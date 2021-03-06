# Sturtevant Auto Tools
This program in its completed state will have a suite of tools used by the employees of Sturtevant Auto Salvage
or various different purposes.  As of right now, the program only supports the original concept program, 
Image Sorter, but the pricing tool database has been constructed and the actual program is in a very early
state.  Login system is now fully implemented and will either transition directly into the pricing tool, which
will act as a hub for the other parts, or a specific hub window that leads into the other windows.

## Features

### Image Sorter
- Scans images stored in a folder defined by the user
- Sorts them according to make, model, and stock number
- Relatively clean GUI instead of old terminal-based system

### Login Screen
- Supports registration of new users, verified by a secret code (currently stored in code, will be storing in SQL soon)
- Supports recovering passwords to a limited extent
- Stores user data in SQL for quick easy access
- Currently does not use SSL since the program is running on an internal network.  Will update if necessary.

### Car Pricing Tool (In Progress)
 - Pulls live scrap pricing index from SQL data (SQL data is from Canadian government release)
 - Calculates price based on scrap price and weight of vehicle
 - Allows employees to have an offline pricing utility, rather than using the slow, web-based one
 - Simple UI making it easy for reusability
 - Search engine used to quickly look up a car based on keywords like year and model
 - Admin tool for adjusting price to weight ratio
 - Admin tool also allows you to adjust prices for specific vehicles to allow for flexible pricing
 - (potential feature) Request a price check from an admin on a vehicle that has questionable pricing

### Image Viewer (planned)
 - Reads images from local computer
 - Displays them in an easy to use fashion
 - Most likely going to be a web-based implementation due to the poor performance of using Java to scale images
 - Could also be coded in Swift since Swift has very nice image scaling

## Installation

Clone the repository:
```sh
$ git clone https://github.com/Jaymus3/Sturtevant-Auto-Tools
```
See __Development__ for more details.

You'll also need [MySQL Connector][Mysq] in order for the SQL database interaction to work.  Open the project with any Java
editor, although I'd recommend an IDE, such as [Eclipse][Ecl] (which is what I personally use)

## Development
Wow, you're actually reading this repository and for some reason interested enough to want to contribute?  If you noticed a 
small issue of some sort, just make an issue for it and I'll get right to it.  If you decide you'd rather fix it up, feel free
to fork the repo, although in its current state it will look weird on systems other than a Mac.
1. Start with setting up an SQL database.  I used the integrated one in OS X and installed [phpmyadmin][phpmy] to manage the
database easily, but you can just use SQL commands if you prefer.

2. Create a new database with the name ``` car_parts ```

3. In ```car_parts ```, create the following tables:

    - ```Account_Index ``` with the columns Name, Username, and Password
    - ``` Indexed_Cars ``` with a single column named StockNumber
    - ``` Make_Model_Index ``` with columns Make and Model
    - Eventually, I'll put an empty sql file up here for easier project setup, but for now I doubt anyone will even be interested
 in setting this up to begin with.
 
 
 4. Create an account named imagesorter with any password you choose (modify the password stored in the program appropriately)

 5. With any luck, your SQL database is ready, and the program *should* start.  If it has errors, look into changing the 
 (currently hardcoded) file paths for the sorting folder and the sorted folder.  

   [phpmy]: <https://www.phpmyadmin.net/>
   [Mysq]: <https://www.mysql.com/products/connector/>
   [Ecl]: <https://eclipse.org/>
