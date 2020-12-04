package myrobot;

import java.io.IOException;

import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * EclipseRobot - a robot by (your name here)
 */
public class SearchRobot extends TeamRobot {
	/**
	 * run: EclipseRobot's default behavior
	 */
	public void run() {
		while (true) {
			// Replace the next 4 lines with any behavior you would like
			// setAhead(100);
			setTurnRadarLeft(100);
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		//fire(1);
       String[] teammates = getTeammates();
       if (teammates != null) {
           for (String member : teammates) {
               if(member.startsWith(e.getName())) return;
           }
       }
		System.out.println(e.getName());
		RobotInfo info = new RobotInfo();
		info.setValue(this, e.getBearingRadians(), e.getDistance(), e.getHeadingRadians());
		try {
			broadcastMessage(info);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void onMessageReceived(MessageEvent event) {
		final double MAX_RANGE = 45;
		final double PI = 180;
	
		// Enemy's coordinate
		double enemyX = 0, enemyY = 0;
	
		// Calculate enemy's bearing
		double bearing = PI - Math.toDegrees(Math.atan(Math.abs(enemyX - getX()) / Math.abs(enemyY - getY())))
				- getHeading();

		// Give width to facing angle
		double randomAngle = Math.random() * MAX_RANGE - (MAX_RANGE / 2);
	
		// Facing the enemy
		turnRight(bearing + randomAngle);

		// Approach the enemy within the range of [0, (distance to enemy)]
		ahead(Math.random() * getDistance(getX(), getY(), enemyX, enemyY));
	}
	
	// Method that calculates distance
	static double getDistance(double x1, double y1, double x2, double y2) {
		double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		return d;
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
