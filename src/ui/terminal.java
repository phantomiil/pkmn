package ui;

public class Terminal {
  // Terminal class handles user interaction in terminal

  public display (int mode) {
    if      (mode == 0) { startup(); }
    else if (mode == 1) { menu(); }
    else if (mode == 2) { fightInit(); }
    else if (mode == 3) { fightAtk(); }
    else if (mode == 4) { fightItem(); }
    else if (mode == 5) { fightPkmn(); }
    else if (mode == 6) { fightEsc(); }
  }

  
}