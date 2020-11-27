package myrobot;

public class Gun {
	EclipseRobot robot;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private long now_tick;
	private double velocity;
	private double bulletspeed = 14; // 20 - 3 * firepower
	public Gun(EclipseRobot init) {
		robot = init;
	}
	public double convert(double bad_rad) {
		double ans = -bad_rad;
		ans += Math.PI / 2.0;
		return ans;
	}
	public void nextAction() {
		long ticks = robot.getTime();
		double now_x = x + velocity * dx * (double)(ticks - now_tick);
		double now_y = y + velocity * dy * (double)(ticks -now_tick);
		double bot = 0;
		double top = 1000;
		for(int i = 0;i < 100;++i) {
			double mid = (bot + top) / 2.0;
			double next_x = now_x + mid * dx * velocity;
			double next_y = now_y + mid * dy * velocity;
			double timing = Math.sqrt((next_x - robot.getX()) * (next_x - robot.getX()) + (next_y - robot.getY()) * (next_y - robot.getY()));
			timing /= bulletspeed;
			if(mid < timing) {
				bot = mid;
			}else {
				top = mid;
			}
		}
		now_x += bot * dx * velocity;
		now_y += bot * dy * velocity;
		now_x = Math.min(robot.getBattleFieldHeight(),Math.max(0,now_x));
		now_y = Math.min(robot.getBattleFieldWidth(),Math.max(0,now_y));
		double TargetRad = Math.atan2(now_y - robot.getY(), now_x - robot.getX());
		double GunRad = convert(robot.getGunHeadingRadians());
		System.out.println(TargetRad);
		System.out.println(GunRad);
		/*
		int hoge = (int)Math.toDegrees(TargetRad - GunRad);
		hoge += 360;
		hoge %= 360;
		if(hoge >= 180) {
			hoge = hoge - 360;
		}
		*/
		double hoge = (TargetRad - GunRad);
		if(hoge < -1.0 * Math.PI) hoge += 2.0 * Math.PI;
		if(hoge > 1.0 * Math.PI) hoge -= 2.0 * Math.PI;
		if(Math.abs(hoge) < 1) {
			robot.setFire(3.0);
		}
		robot.setTurnGunLeftRadians(hoge);
		System.out.println(hoge);
	}
	public void setValue(double enemy_relative_rad,double distance,double enemy_nowrad) {
		double enemy_rad = -enemy_relative_rad + convert(robot.getHeadingRadians());
		x = robot.getX() + Math.cos(enemy_rad) * distance;
		y = robot.getY() + Math.sin(enemy_rad) * distance;
		now_tick = robot.getTime();
		enemy_nowrad = convert(enemy_nowrad);
		dx = Math.cos(enemy_nowrad);
		dy = Math.sin(enemy_nowrad);
		velocity = 10;
	}
}
