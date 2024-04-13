---
layout: page
title: Zhang Tianyao's Project Portfolio Page
---

### Project: CCBOT

CCBOT is a desktop application for managing your job application details.** While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort person list.
    * What it does: allows the user to sort the person list by name, company name, job difficulty, priority, salary, and interview date.
    * Justification: This feature improves the product significantly because a user can easily find the person he/she wants to check.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the job difficulty model.
    * What it does: allows the program auto calculate the job difficulty based on the job description.
    * Justification: This feature improves the product significantly because a user can easily identify the job difficulty.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
    * Add salary range to the person card (Pull requests [\#20]())
    * Fix add command examples (Pull requests [\#35]())
    * Add job difficulty comparator (Pull requests [\#69]())
    * Add priority to person card (Pull requests [\#39]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `sort` and `job difficulty` [\#93]() [\#86]()
        * Added documentation for the constraints of the `add`, `edit` and `add resume` command [\#169]()
    * Developer Guide:
        * Added implementation details of the `sort` feature.
          * Added implementation details of the `job difficulty` feature.[\#54]()
        * Update UML diagrams. [\#37]()
        * Add UML diagrams for `sort` command. [\#43]()

