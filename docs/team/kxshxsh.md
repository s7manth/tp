# Kashish'a Project Portfolio Page

---
## Project: TAilor

TAilor is a desktop app for managing contacts. The user interacts with it using a CLI, and it has a GUI
created with JavaFX. It is written in Java, and has about 10 kLoC. Through TAilor we hope to make most common,
tedious administrative tasks, that Teaching Assistants encounter, easier and more efficient to carry out.
---

###Summary of Contributions
My contributions to the project can be found below:

* My **Code Contributions** can be found [here](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=kxshxsh&breakdown=true)

* **Enhancements Implemented**: 
   1. **Feature #1: Import-csv command** 
      * What it does:
        Allows our user to import a csv file, given the required column headers are present. All student data from the 
        csv file is imported into the TAilor database for further processing.
      * Justification:
        This feature improves the product significantly because it allows user to forgo the tedious task of manually
        entering every student's data into TAilor's database. All they need to do is export the excel file from LumiNUS,
        with all the requested fields, convert to csv and then import with the file path!
      * Highlights:
        * It adds students additively to the already present student list instead of just replacing the previous entries. 
        This is a highlight since the user can use this aspect of the command to either update the student list at once,
        or even to import data from different csv files (that could have been from different modules). 
        * I was also flexible with the format of the file path taken in, the user can use either an absolute path or a 
        relative one and it shall execute without error.
        * As such this feature takes into consideration many possible user scenarios and has something for everyone - this
        according to me is its highlight.
      * Challenges:
        * Implementing a whole CsvUtil class was a challenge since I had to use an open-source, third party library Opencsv 
          and also had to consider the different errors that a user could make while exporting or drafting their csv files.
        * The defensive coding in import-csv command's implementation is therefore high and also took a lot of work for the same.
      * Time spent on this (estimate):
        600 mins

   2. **Feature #2: implementing the base for groups**
      * What it does:
        It associates a student with a group (alongside the newly-implemented module field) so that students can essentially
        be categorised or placed into tutorial groups. This allows for the same tutor to be able to teach the same
        student under different modules (and thus tutorial groups).
      * Justification and Highlights:
        This feature allows for further categorization and organization. Especially required for those situations when
        one user tutors more than one (tutorial) group under a module. It is important to ensure that a tutor can:
        * view filtered lists on the basis of different tutorial groups and
        * mail all students in one group
        This is where the group attribute merges seamlessly, and also enhances, functions implemented by my team members.
        This is undoubtedly the highlight of this feature, the integrity and cohesion that it enhances.
      * Challenges:
        While I thought the implementation would have been very similar to that of the former "address" attribute, I ran
        into an issue with the UI not working suddenly. Since this was the first feature that I ever implemented, trying
        to fix the GUI issue was quite challenging to narrow down and rectify. I was quite thorough
      * Time spent on this (estimate):
        300 mins
   3. **Feature #3: implementing the code base for set-default-group command**    
      * What it does:
        It associates a default group value to a module that can be set to what the user supplies through the arguments
        of this command. If a default group value is set for the specific module that is mentioned in a following add command,
        then the group parameter required in the same add command actually becomes optional. 
        Example:
          * `set-default-group m/CS2103T g/W12-1` followed by `add n/Kashish Varshney e/e0589909@u.nus.edu a/A0225771A m/CS2103T`
            is a valid command and Kashish Varshney would be added to m/CS2103T g/W12-1 as a result
          * if a group parameter was provided in the add command though then that would be prioritised over the default group
            for that command's execution
      * Justification:
        This command helps our users in the situation where they would have to bulk-add a large number of students, ideally
        to the same tutorial group, to the student list.

      

* **Contribution to the User Guide**: the initial version has been worked on rather equally. Specific section
   details to be added soon!
* **Contributions to the Developer Guide**: to be added soon.
* **Contributions to team-based tasks**:
    - played a role in renaming the product
    - more to be added soon!
6. **Reviewing/mentoring Contributions**: to be added soon
7. **Contributions outside the project team:** to be added soon
