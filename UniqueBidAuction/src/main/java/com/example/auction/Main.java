package com.auction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Auction auction = new Auction(10);
        boolean tieGoesToFirstBidder = false;

        boolean restarted;
        do {
            System.out.println("\n--- Starting Auction (Min: $" + auction.getMinimumBid() + ") ---");
            System.out.println("Enter bids in format: <bidderId> <amount> (or type 'end' to finish bidding)");

            // Accept bids until user types 'end'
            while (true) {
                System.out.print("Enter bid: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("end")) {
                    break;
                }

                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("❌ Invalid input. Use: <bidderId> <amount>");
                    continue;
                }

                try {
                    int bidderId = Integer.parseInt(parts[0]);
                    int amount = Integer.parseInt(parts[1]);
                    auction.placeBid(new Bid(bidderId, amount));
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid numbers. Try again.");
                }
            }

            System.out.println("\n--- Auction Closed ---");
            restarted = auction.endAuction(tieGoesToFirstBidder);

        } while (restarted);

        scanner.close();
    }
}
