---
layout: page
title: Han Yang's Project Portfolio Page
---

### Project: TAilor

TAilor is a desktop app for managing contacts. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=festivecat&breakdown=true)

* **Enhancements implemented**:
  * **Feature 1**: Extending `Find` command
    * What it does:
      Originally: `Find` only allows users to search contacts in the database using names.
      Now, `Find` allows users to search contacts using names, phone, tags, email etc. (All fields in a Person)
    * Justification:
      This feature improves the product significantly because it can be used to find and view the
      students/contacts easily. Makes the original command more robust.
    * Highlights:
      Incorporated many individual classes for each of the search conditions, as well as a compounded search condition
      that allows searching based on all conditions passed into it.
    * Time spent on this (estimate):
      210mins
  * **Feature 2**: Implementing `Mod` class
    * What it does:
      This is basically another field/detail about a person, like Name and Email.
    * Justification:
      This feature makes our application more targeted towards our target user, Teaching Assistants.
      By having this `Mod` class, Teaching Assistants can now tag students with a certain Class, ie `CS2030S`
      To better keep track of their students.
    * Time spent on this (estimate):
      150mins

* **Enhancements to existing features**:
  * Add Mod class/field (Pull request [\#70]())
  * Find command (Pull requests [\#75]())

* **Documentation**:
  * User Guide (UG):
    * Reviewed UG for v1.1, made minor formatting and grammatical changes.
    * Modified part on Find Command to incorporate extended features.
  * Developer Guide (DG):
    * Changed some "Address Book" into "Contact List" to better represent the project.

* **Contribution to team-based tasks**:
  * Made team organisation and repository at the beginning.
  * Transferred half of User Stories into Issues to track.
  * Incorporated a standardised commit message format: [\#67]()

* **Project management**:
  * Drove team meetings forward, increasing productivity.
  * Up-kept a list of deliverables per week, updating it frequently so that everyone
  is on the same page.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#42]() [\#52]()
