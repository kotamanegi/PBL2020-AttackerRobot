package myrobot;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * EclipseRobot - a robot by (your name here)
 */
public class EclipseRobot extends TeamRobot
{
	/**
	 * run: EclipseRobot's default behavior
	 */
	private Gun nowGun;
	public void run() {
		nowGun = new Gun(this);
		setMaxTurnRate(2);
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			//setAhead(100);
			setTurnRadarLeft(100);
			setTurnGunRight(100);
			nowGun.nextAction();
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		//fire(1);
		nowGun.setValue(e.getBearingRadians(), e.getDistance(), e.getHeadingRadians());
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
