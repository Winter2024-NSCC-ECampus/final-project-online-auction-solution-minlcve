# Unique Bid Blind Online Auction

## Overview
This project is a Java-based implementation of a Unique Bid Blind Online Auction system. 
Bidders submit integer bids, and the highest **unique** bid wins the auction. 
If there is a tie at the highest bid, the system either restarts the auction or awards the win to the first bidder, depending on the selected rule.

## Features
- Accepts multiple bids from users in real time
- Ignores bids below the minimum allowed
- Automatically handles thousands of bids
- Handles tie-breaking logic:
  - Option 1: Restart auction with higher minimum
  - Option 2: First bidder wins (toggle available)
- Saves auction results to `auction_results.txt`

## How It Works
1. Run the program in your terminal.
2. Enter bids using the format: `bidderId bidAmount`
3. Type `end` to close bidding and process results.
4. View results in the console and in the output file.

## Technologies Used
- Java 18
- IntelliJ IDEA (on macOS)
- Maven

## Files
- `Bid.java` – defines the structure of a bid
- `Auction.java` – contains all auction logic
- `Main.java` – entry point; handles input and runs auction
- `auction_results.txt` – generated file storing auction logs

## How to Run
1. Clone or download the repo
2. Open in IntelliJ (or another Java IDE)
3. Run `Main.java`
4. Start entering bids!

## Author
Jasmine Fowler
