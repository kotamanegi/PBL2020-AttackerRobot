package myrobot;

import robocode.MessageEvent;
import robocode.TeamRobot;

class SearchRobot extends TeamRobot {

  public void onMessageReceived(MessageEvent event) {

    final double MAX_RANGE = 45;
    final double PI = 180;

    // 敵の座標
    double enemyX = 0, enemyY = 0;

    // メッセージの取得と相手座標の取得（よくわからない）
    // Serializable message = event.getMessage();

    // 相手の方位角を計算
    double bearing = PI - Math.toDegrees(Math.atan(Math.abs(enemyX - getX()) / Math.abs(enemyY - getY())))
        - getHeading();
    // 向く角度に幅を持たせる
    double randomAngle = Math.random() * MAX_RANGE - (MAX_RANGE / 2);
    // 相手の方を向く
    turnRight(bearing + randomAngle);
    // 相手の方に[0, (相手までの距離)]の範囲で近づく
    ahead(Math.random() * getDistance(getX(), getY(), enemyX, enemyY));
  }

  // 距離を算出するメソッド
  static double getDistance(double x1, double y1, double x2, double y2) {
    double d = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    return d;
  }
}