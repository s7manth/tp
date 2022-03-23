---
layout: page
title: User Guide
---

TAilor is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI)
while still having the benefits of a Graphical User Interface (GUI). If you are a Computer Science Teaching
Assistant who can type fast, TAilor can get your contact management tasks done faster than traditional GUI apps.


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TAilor.jar` from [here](https://github.com/AY2122S2-CS2103T-W12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TAilor Application.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note
   how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter
   will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe a/A0123456P e/johnd@example.com m/CS2103T g/W12` : Adds a student

     named `John Doe` to the Contact List.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME a/STUDENT_NUMBER`, `a/STUDENT_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `a/A0123456H a/A1111111H`, only `a/A1111111H` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the student roster.

Format: `add n/NAME a/STUDENT_NUMBER e/EMAIL m/MOD g/GROUP [t/TAG]…​`


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:

* `add n/John Doe a/A1234567L e/johnd@example.com m/CS2030S g/B12G`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com m/CS2100 g/T1 p/1234567 t/criminal`


### Listing all persons : `list`

Shows a list of all persons in the student roster.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the student roster.

Format: `edit INDEX [n/NAME] [a/STUDENT_NUMBER] [e/EMAIL] [m/MOD] [g/GROUP] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 a/A1122334X e/johndoe@example.com` Edits the studentNumber and email address of the 1st person to 
   be `A1122334X` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose details contain any of the given keywords.

Format: `find PREFIX/KEYWORD [KEYWORD] [PREFIX/KEYWORD [KEYWORD]]…​`

* The prefixes used are the same as other commands:
  * `n/` for name
  * `a/` for studentNumber
  * `e/` for email
  * `m/` for module
  * `g/` for group
  * `t/` for tags
* Multiple keywords can be given for each tag.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the specified prefix will be searched
* For names, only full words will be matched e.g. `Han` will not match `Hans`
* For the rest, partial words will be matched e.g. `exam` will match `abc@example.com`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find n/alex david'](images/findAlexDavidResult.png)

### Mailing a particular student based on index : `mail-index`

Mails a person from the student roster based on the index number shown in the application.

Format: `mail-index INDEX`

* This opens the default email application on the system with the specified mail address pre-filled at the receiver's
  address.

Example:
* `mail-index 2` would open the default mail on the system with the "to" box filled with the
  specified mail. Now, the email is ready to be sent to the person with the index 2 as shown on the application.

### Mailing student subset based on arguments : `mail-x`

Mails a subset of students from the contact list. This is a broader version of mail functionality as it can
cover multiple students at once in order to mail them simultaneously.

Format: `mail-x [e/EMAIL] [g/GROUP] [m/MOD] [n/NAME]`

* This opens the default email application on the system with all the mail addresses specified by the arguments.
* The mail addresses are additively added to the receiver's box.
* This can be used to specify multiple prefix based arguments to send the same mail in a single go.

Example:
* `mail-x e/johndoe@example.com n/Alex` would open the default mail on the system with the "to" box filled with the
  all the mail addresses covered by the arguments specified. Now, the email is ready to be sent to `johndoe@example.com`
  and Alex.

### Bulk Emails to everyone on the database : `mail-all`

Mails everybody in the student roster.

Format: `mail-all`

* Opens the default email application on the system with all email addresses pre-filled in the receiver's
  field.

### Deleting a person : `delete`

Deletes the specified person from the student roster.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the student roster.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the student roster.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Adding a new task: `newtask`

Adds a new Task with a given description and deadline.

Format: `newtask DESCRIPTION by/DATETIME`

* Description must be non-empty. Ie, it cannot consist of all spaces.
* DATETIME has to be in the format of : `YYYY-MM-DDThh:mm` , where
  * YYYY represents a 4-digit year
  * MM represents a 2-digit month (so March will be 03, November 11)
  * DD represents a 2-digit day (1st day of the month will be 01)
  * hh represents the hour, as in 24-hour (ie 3am is 0300, 3pm is 1500)
  * mm represents the minute.
  * The dashes `-`, colons `:` and the `T` must be in the corresponding positions.
  * The Time and Date being input must be valid. Ie, it is not possible to input a task
    with a deadline of 31st February.

Examples:
* `newtask Do Homework by/2022-03-21T23:59` creates a task with description of "Do Homework" and is due on 21 March 2022, 11:59pm.

### Deleting an existing task: `deltask`

Deletes the specified person from the task list.

Format: `deltask INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete 2` deletes the 2nd person in the task list.

---

### Saving the data

TAilor data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file

TAilor data are saved as a JSON file `[JAR file location]/data/contactlist.json`.
Task List data is also saved as a JSON file `[JAR file location]/data/tasklist.json`. Advanced users are
welcome to update the data directly by editing those data files.

<div markdown="span" class="alert alert-warning"> :exclamation: **Caution:**
If your changes to the data file makes its format invalid, TAilor will discard all data and start
with an empty data file at the next run.

To reset the files, perform any command that changes the contact list or task list (ie add new task, add new contact).
WARNING: This will override the pre-existing data with the new data you entered.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the
       file that contains the data of your previous TAilor home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action         | Format, Examples                                                                                                                                                |
|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `add n/NAME p/PHONE_NUMBER e/EMAIL m/MODULE g/GROUP [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com m/CS2100 g/W12 t/friend t/colleague` |
| **Clear**      | `clear`                                                                                                                                                         |
| **Delete**     | `delete INDEX`<br> e.g., `delete 3`                                                                                                                             |
| **Edit**       | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [m/MODULE] [g/GROUP] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                            |
| **Exit**       | `exit`                                                                                                                                                          |
| **Find**       | `find PREFIX/KEYWORD [MORE_KEYWORDS] [PREFIX/KEYWORD [MORE_KEYWORDS]]`<br> e.g., `find n/James Jake`                                                            |
| **List**       | `list`                                                                                                                                                          |
| **Mail Index** | `mail-index`<br> e.g., `mail 2`                                                                                                                                 |
| **Mail X**     | `mail-x`<br> e.g., `mail e/johndoe@example.com n/Alex`                                                                                                          |
| **Mail All**   | `mail-all`                                                                                                                                                      |
| **Help**       | `help`                                                                                                                                                          |
