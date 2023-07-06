# Change Calculator

The Change Calculator is a Java program that calculates the change to be dispensed in the fewest number of coins based on a given amount. It simulates a change vending machine by using a predefined inventory of coins and bills.

## Initial Values

The program is initialized with the following coin denominations and quantities:

- 5 zł (500 gr) - 1 coin
- 2 zł (200 gr) - 3 coins
- 1 zł (100 gr) - 5 coins
- 50 gr - 10 coins
- 20 gr - 20 coins
- 10 gr - 200 coins
- 5 gr - 100 coins
- 2 gr - 100 coins
- 1 gr - 10000 coins

These initial values represent the available coins and bills in the vending machine.

## Usage

1. Run the program in a Java IDE or from the command line.
2. Enter the change amount (in zł) when prompted.
3. The program will calculate and display the minimum number of coins to dispense for the given change amount.
4. The inventory of coins will be updated accordingly, reducing the quantity of coins used.

The program will continue to prompt for a change amount until an invalid or zero amount is entered, at which point the program will exit.
