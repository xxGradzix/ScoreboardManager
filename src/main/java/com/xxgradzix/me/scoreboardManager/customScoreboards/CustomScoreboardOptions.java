package com.xxgradzix.me.scoreboardManager.customScoreboards;

public class CustomScoreboardOptions {

  private CustomScoreboardTabHealthStyle tabHealthStyle;
  private boolean showHealthUnderName;

  public CustomScoreboardOptions(CustomScoreboardTabHealthStyle tabHealthStyle, boolean showHealthUnderName) {
    this.tabHealthStyle = tabHealthStyle;
    this.showHealthUnderName = showHealthUnderName;
  }

  public static CustomScoreboardOptions defaultOptions = new CustomScoreboardOptions(CustomScoreboardTabHealthStyle.NONE, false);

  public CustomScoreboardTabHealthStyle getTabHealthStyle() {
    return tabHealthStyle;
  }

  public boolean shouldShowHealthUnderName() {
    return showHealthUnderName;
  }

  /**
   * The scoreboard must be updated for this change to take effect.
   * @param showHealthUnderName
   */
  public void setShowHealthUnderName(boolean showHealthUnderName) {
    this.showHealthUnderName = showHealthUnderName;
  }

  /**
   * The scoreboard must be updated for this change to take effect.
   * @param tabHealthStyle
   */
  public void setTabHealthStyle(CustomScoreboardTabHealthStyle tabHealthStyle) {
    this.tabHealthStyle = tabHealthStyle;
  }
}


