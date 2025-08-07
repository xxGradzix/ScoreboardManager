package com.xxgradzix.me.scoreboardManager.customScoreboards;

import com.xxgradzix.me.scoreboardManager.customScoreboards.wrapper.WrappedHealthStyle;

public enum CustomScoreboardTabHealthStyle {
  NONE,
  HEARTS,
  NUMBER;

  public WrappedHealthStyle toWrapped() {
    switch (this) {
      case HEARTS: return WrappedHealthStyle.HEARTS;
      case NONE: return WrappedHealthStyle.NONE;
      case NUMBER: return WrappedHealthStyle.NUMBER;
    }

    return WrappedHealthStyle.NONE;
  }
}
