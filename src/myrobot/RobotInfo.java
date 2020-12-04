package myrobot;

import java.io.Serializable;

import robocode.TeamRobot;

public class RobotInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1321354L;
	public double x;
	public double y;
	public double dx;
	public double dy;
	public long now_tick;
	public double velocity;
	public double convert(double bad_rad) {
		double ans = -bad_rad;
		ans += Math.PI / 2.0;
		return ans;
	}
	public void setValue(TeamRobot robot,double enemy_relative_rad,double distance,double enemy_nowrad) {
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
