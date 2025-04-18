package com.employeemis.cli;

import java.util.Stack;

public abstract class Navigator {
  private final Stack<String> history;

  public Navigator() {
    history = new Stack<>();
  }

  public final void clear() {
    history.clear();
  }

  public final String current() {
    return String.join("/", history);
  }

  public final void replace(String route) {
    clear();
    forward(route);
  }

  public final void forward(String route) {
    history.add("/" + route
      .replace("/", "")
      .replace(" ", "")
    );
  }

  public final void backward(String url) {
    for (String ellipsis : url.split("/")) {
      if (!ellipsis.equals("..."))
        break;
      else
        history.pop();
    }
  }
}
