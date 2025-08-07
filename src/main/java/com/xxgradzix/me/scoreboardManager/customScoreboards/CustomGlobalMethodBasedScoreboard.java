package com.xxgradzix.me.scoreboardManager.customScoreboards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CustomGlobalMethodBasedScoreboard extends CustomGlobalScoreboard {
  private String title = "";
  private List<String> lines = new ArrayList<>();

  public CustomGlobalMethodBasedScoreboard(String id, CustomScoreboardOptions options) {
    super(id, options);

    setTitleSupplier(() -> title);
    setLinesSupplier(() -> lines);
  }

  public CustomGlobalMethodBasedScoreboard(String id) {
    this(id, CustomScoreboardOptions.defaultOptions);
  }

  public void setTitle(String title) {
    this.title = title;
    updateScoreboard();
  }

  public void setLines(List<String> lines) {
    this.lines = lines;
    updateScoreboard();
  }

  public void setLines(String... lines) {
    setLines(Arrays.asList(lines));
  }
}
