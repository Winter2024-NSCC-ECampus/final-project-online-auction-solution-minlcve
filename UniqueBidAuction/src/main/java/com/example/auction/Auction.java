package com.auction;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Auction {
    private int minimumBid;
    private Map<Integer, Queue<Integer>> bidMap;
    private List<String> log; // To store log lines

    public Auction(int minimumBid) {
        this.minimumBid = minimumBid;
        this.bidMap = new HashMap<>();
        this.log = new ArrayList<>();
    }

    public void placeBid(Bid bid) {
        if (bid.getAmount() < minimumBid) {
            String msg = "❌ Bid rejected: $" + bid.getAmount() + " is below minimum bid of $" + minimumBid;
            System.out.println(msg);
            log.add(msg);
            return;
        }

        bidMap.putIfAbsent(bid.getAmount(), new LinkedList<>());
        bidMap.get(bid.getAmount()).add(bid.getBidderId());

        String msg = "✅ Bid placed: Bidder " + bid.getBidderId() + " bid $" + bid.getAmount();
        System.out.println(msg);
        log.add(msg);
    }

    public boolean endAuction(boolean tieGoesToFirstBidder) {
        if (bidMap.isEmpty()) {
            log.add("❌ No bids received.");
            System.out.println("No bids received.");
            writeResultsToFile();
            return false;
        }

        List<Integer> bidAmounts = new ArrayList<>(bidMap.keySet());
        bidAmounts.sort(Collections.reverseOrder());

        for (int amount : bidAmounts) {
            Queue<Integer> bidders = bidMap.get(amount);
            if (bidders.size() == 1) {
                String msg = "🏆 Winner: Bidder " + bidders.peek() + " with unique highest bid of $" + amount;
                System.out.println(msg);
                log.add(msg);
                writeResultsToFile();
                return false;
            } else if (bidders.size() > 1) {
                if (tieGoesToFirstBidder) {
                    String msg = "🤝 Tie at $" + amount + ". First bidder wins: Bidder " + bidders.peek();
                    System.out.println(msg);
                    log.add(msg);
                    writeResultsToFile();
                    return false;
                } else {
                    String msg = "❗ Tie detected at $" + amount + " between " + bidders;
                    System.out.println(msg);
                    log.add(msg);
                    int newMin = amount + 1;
                    log.add("🔁 Auction will restart with minimum bid: $" + newMin);
                    restartAuction(newMin);
                    return true;
                }
            }
        }

        log.add("❌ No valid unique bids.");
        writeResultsToFile();
        return false;
    }

    public void restartAuction(int newMinimumBid) {
        this.minimumBid = newMinimumBid;
        this.bidMap.clear();
        this.log.add("🔁 Auction restarted. New minimum bid: $" + newMinimumBid);
    }

    public int getMinimumBid() {
        return minimumBid;
    }

    private void writeResultsToFile() {
        String filename = "auction_results.txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("=== Auction Results (" + LocalDateTime.now() + ") ===\n");
            for (String entry : log) {
                writer.write(entry + "\n");
            }
            writer.write("============================================\n\n");
            System.out.println("📁 Results saved to " + filename);
        } catch (IOException e) {
            System.out.println("❌ Failed to write results: " + e.getMessage());
        }
    }
}
