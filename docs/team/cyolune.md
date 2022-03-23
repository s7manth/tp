---
layout: page
title: Jan's Project Portfolio Page
---

### Project: TAilor

TAilor is a desktop app for managing contacts. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=festivecat&breakdown=true)

* **Enhancements implemented**:
  * **Feature 1**: Undo command
    * What it does:
    Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification:
    This feature improves TAilor significantly because a user can make mistakes in commands and we 
    should provide a convenient way to rectify them.
    * Highlights:
    This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of 
    design alternatives. The implementation too was challenging as it required changes to existing commands, 
    and modifications to the ModelManager class was done with the addition of new classes to increase extendability of 
    this feature (in the case of additions of new content to be undone other than contacts and tasks)
    * Time spent on this (estimate):
    600 mins
  * **Feature 2**: Refill previous command
    * What it does:
    Allows the user to refill the command textbox with a command that was previously entered by pressing the 'up' button. Commands
    entered at a later time can be reached by pressing the 'down' button as well.
    * Justification:
    This feature improves TAilor significantly and works very well with undo as a user who has made a mistake in their command
    can undo and quickly fill the command textbox with the mistyped command, allowing them to quickly correct their typo 
    and enter the intended command.
    * Highlights:
    This feature required extensive research on JavaFX and how events such as keypresses are captured.
    * Time spent on this (estimate):
    240 mins

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide (UG):
    * Reviewed UG for v1.1, made minor formatting and grammatical changes.
    * Updated UG for parts I planned on implementing
  * Developer Guide (DG):
    * Changed some "Address Book" into "Contact List" to better represent the project
    * Updated DG for parts I modified in v1.3.
      * For example, the model class diagram was edited to include the new VersionedContent class
      * A class diagram representing the VersionedContent class was included as well
      * Reformatted the undo/redo texts and uml diagrams.

* **Contribution to team-based tasks**:
  * Transferred half of User Stories into Issues to track.
  * Made v1.2 milestone tracker

* **Project management**:
  * Drove team meetings forward, increasing productivity.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#72]()
