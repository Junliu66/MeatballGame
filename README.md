# Super Meat Ball
### Running the Project
When you first open the project you will need to import the gradle project. There should be a notification near the bottom of the screen.

To run the project, go to desktop/src/com.meat.desktop/DesktopLauncher.java and run "DesktopLauncher". 
![running](https://i.imgur.com/rxi5gN2.png)

***It will fail.*** You need to add "/core/assets" to the 'working directory'.
![configuration](https://i.imgur.com/5LhWqrb.png)

### Project Proposal
https://github.com/FH-Fa17-CS40A-22138/team01-project02/blob/master/Project2Proposal.md

### Project Structure
The project starts in desktop/src/com.meat.desktop/DesktopLauncher.java, but the majority of code is in core/src/com.meat/

The assets (images, sounds, level files) of the project are availible in core/assets. To open the levels (.tmx) and the tilesets (.tsx), you need the Tile Map Editor: http://mapeditor.org

#### Submitted Files
- desktop/src/com.meat.desktop/
  - DesktopLauncher.java
- core/assets/* (not including all the assets here for brevity)
- core/src/com.meat/
  - Objects/
    - Obstacle.java
  - Config.java
  - CongratsScreen.java
  - ControlMenu.java
  - Enemy.java
  - FixedPathEnemy.java
  - GameOverScreen.java
  - Garlic.java
  - HandIntro.java
  - HandPointer.java
  - HelpScreen.java
  - Invincibility.java
  - LevelSelectScreen.java
  - MainGame.java
  - MainMenu.java
  - MeatGame.java
  - Pepper.java
  - Pickup.java
  - Player.java
  - PlayerChasingEnemy.java
  - PlayerModifier.java
  - SauceTrail.java
  - SpeedUp.java
  - SplashScreen.java
  - Tomato.java
  - Util.java


### Documentation
TODO

### Source Code + Libraries
- Libgdx: open-source game engine in Java utiliziting Lightweight Java Game Library
- Box2D: 2D physics engine
- Tiled Map Editor: 2D map and tileset editor

### Video
TODO
