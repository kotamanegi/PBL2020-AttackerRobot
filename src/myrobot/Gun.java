package myrobot;

public class Gun {
	EclipseRobot robot;
	private double bulletspeed = 14; // 20 - 3 * firepower
	public Gun(EclipseRobot init) {
		robot = init;
	}
	public double convert(double bad_rad) {
		double ans = -bad_rad;
		ans += Math.PI / 2.0;
		return ans;
	}
	public void nextAction(RobotInfo target) {
		long ticks = robot.getTime();
		double now_x = target.x + target.velocity * target.dx * (double)(ticks - target.now_tick);
		double now_y = target.y + target.velocity * target.dy * (double)(ticks - target.now_tick);
		double bot = 0;
		double top = 1000;
		for(int i = 0;i < 100;++i) {
			double mid = (bot + top) / 2.0;
			double next_x = now_x + mid * target.dx * target.velocity;
			double next_y = now_y + mid * target.dy * target.velocity;
			double timing = Math.sqrt((next_x - robot.getX()) * (next_x - robot.getX()) + (next_y - robot.getY()) * (next_y - robot.getY()));
			timing /= bulletspeed;
			if(mid < timing) {
				bot = mid;
			}else {
				top = mid;
			}
		}
		now_x += bot * target.dx * target.velocity;
		now_y += bot * target.dy * target.velocity;
		now_x = Math.min(robot.getBattleFieldHeight(),Math.max(0,now_x));
		now_y = Math.min(robot.getBattleFieldWidth(),Math.max(0,now_y));
		double TargetRad = Math.atan2(now_y - robot.getY(), now_x - robot.getX());
		double GunRad = convert(robot.getGunHeadingRadians());
		double hoge = (TargetRad - GunRad);
		System.out.println("Target " + hoge);
		if(hoge < -1.0 * Math.PI) hoge += 2.0 * Math.PI;
		if(hoge > 1.0 * Math.PI) hoge -= 2.0 * Math.PI;
		if(Math.abs(hoge) < 1) {
			robot.setFire(3.0);
		}
		robot.setTurnGunLeftRadians(hoge);
	}
}
