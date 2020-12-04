package myrobot;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * EclipseRobot - a robot by (your name here)
 */
public class EclipseRobot extends TeamRobot implements Droid
{
	/**
	 * run: EclipseRobot's default behavior
	 */
	private Gun nowGun;
	private RobotInfo info;
	public void run() {
		nowGun = new Gun(this);
		setMaxTurnRate(2);
		info = new RobotInfo();
		while(true) {
			setTurnGunRight(100);
			nowGun.nextAction(info);
			execute();
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
	}
	
	public void onMessageReceived(MessageEvent e) {
		info = (RobotInfo) e.getMessage();
		System.out.println(this.getTime());
		System.out.println(info.x);
		System.out.println(info.y);
	}
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
