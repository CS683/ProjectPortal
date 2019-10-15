# CS683 Lab 2 - Basic UI

In this lab, we want to learn basic UI components such as activities, fragments, views, viewgroups, layouts and different widgets.

Based on the given code, you will need to 

## 1. Modify the Project Model class

Besides the project title and summary, we also want to know other information about the project:
1. Authors of the project
2. Project links: (e.g. youtube links, github links, etc)
3. Is this project your favorite project?
4. Keywords that can be used to search the project
5. Any additional information you would like to add

(Hint: You should modify the Project model class to include the above information, e.g. boolean isFavorite, List<String> links).

## 2. Modify the Project Detail UI
1. Explore the design palette, pick the proper layout(s) and widgets to display above information. 
2. Add a proper widget to let the user set (or unset) a project as their favorite project. (_Optional_)

(Hint: you can use Checkbox or Switch to show isFavorite. To change its value, you can use CompoundButton.OnCheckedChangeListener.
The callback function defined by this listener is onCheckedChanged(CompoundButton buttonView, boolean isChecked). 
It takes two parameters. The boolean parameter isChecked shows the new checked state of the button, 
which can be used to set isFavorite value. 
Check https://developer.android.com/reference/android/widget/CompoundButton.OnCheckedChangeListener for more details.)
