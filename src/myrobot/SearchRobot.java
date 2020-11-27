package myrobot;
import java.io.IOException;

import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * EclipseRobot - a robot by (your name here)
 */
public class SearchRobot extends TeamRobot
{
	/**
	 * run: EclipseRobot's default behavior
	 */
	public void run() {
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			//setAhead(100);
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
