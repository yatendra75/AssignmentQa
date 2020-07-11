___________________Dependency handling __________________
pom.xml-> maven clean install
>Update your maven project

__________Entry point of project: To run locally__________
1. testng.xml-> src/main/resources/testng.xml

__________Directory Details : src/main/java_________________

For all utilities - utilities/
  1. ForCommon and generic xpath constants- utilities/ConstantsAndValue
  2. For all xpaths - utilities/Locators
  3. For loading data or sending data - utilities/fileloader utilities
	 {Note: data is reading in form of constants from ConstantsAndValue class, so user should make changes in there}

For driver initialization & all selenium actions handler -base/

For writing functionalities/Page automation code-pages/
		1. LoginPage: Login method mentioned
		2. GitFunctions: all given challenges methods written here

For writing testcases- /testcases

_____________Directory Details: src/main/resources_________________
All property files , drivers , testng.xml 