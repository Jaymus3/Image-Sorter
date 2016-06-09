# Image Sorter
This program is designed to take images from a given directory with a certain file appendage, and sort them with a given stock number, make, and model.  It has several hardcoded values such as the file path, so usage of this in any other environment would take some setup.

## Features

  - Stores index of makes and models for future usage
  - Stores index of cars that pictures have been taken of
  - Prompts the user to delete pictures if a car already exists

### Installation

Clone the repository:
```sh
$ git clone https://github.com/Jaymus3/Image-Sorter
```
You'll also need [MySQL Connector][Mysq] in order for the SQL database interaction to work.  Open the project with your favorite Java editor, such as [Eclipse][Ecl]

### Development

See something that could use improvement?  Great!  Just fork the repo, make your changes, and make a pull request detailing what you fixed up.



   [Mysq]: <https://www.mysql.com/products/connector/>
   [Ecl]: <https://eclipse.org/>
