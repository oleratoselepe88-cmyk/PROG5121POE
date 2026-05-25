package prog5121poe;

import java.util.ArrayList;
import java.util.Random;

/**
 * ================================================
 * PROG5121 - Part 2
 * Author    : Olerato Selepe
 * Student No: ST10511014
 * Date      : April 2026
 * Purpose   : This class handles all messaging
 *             functionality including creating,
 *             sending, storing and displaying
 *             messages.
 * ================================================
 */
public class Message {

  
    private String messageID;

    // Stores the recipient's cell number
    private String recipientCell;

    // Stores the message text typed by the user
    private String messageText;

    // Stores the auto-generated hash for this message
    private String messageHash;

    // Tracks how many messages have been sent in total
    private static int totalMessagesSent = 0;

    // Tracks total number of messages created
    private static int messageCounter = 0;

    // Stores all sent messages in a list
    private static ArrayList<String> sentMessages = new ArrayList<>();

    // Stores all stored messages in a list
    private static ArrayList<String> storedMessages = new ArrayList<>();

    // Stores all message hashes
    private static ArrayList<String> messageHashes = new ArrayList<>();

    // Stores all message IDs
    private static ArrayList<String> messageIDs = new ArrayList<>();

    // Stores all recipients
    private static ArrayList<String> recipients = new ArrayList<>();

   
   
    public Message(String recipientCell, String messageText) {
        this.recipientCell = recipientCell; // Save recipient number
        this.messageText   = messageText;   // Save message text
        messageCounter++;                   // Increment message count

        // Auto-generate a unique message ID
        this.messageID   = generateMessageID();

        // Auto-generate the message hash
        this.messageHash = createMessageHash();
    }

   
  
    private String generateMessageID() {
        // Create a Random object to generate random numbers
        Random random = new Random();

        // Build a 10-digit ID using a StringBuilder
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // Add a random digit (0-9) each time
            id.append(random.nextInt(10));
        }
        return id.toString(); // Return the completed ID
    }

 
    public boolean checkMessageID() {
        // Check that the message ID length does not exceed 10
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        
        return recipientCell.matches("^\\+\\d{1,3}\\d{7,10}$");
    }

 
  
    public String createMessageHash() {
        // Get the first two characters of the message ID
        String firstTwo = messageID.substring(0, 2);

        // Get the last two characters of the message ID
        String lastTwo = messageID.substring(messageID.length() - 2);

        // Split the message into individual words
        String[] words = messageText.trim().split(" ");

        // Get the first word of the message
        String firstWord = words[0];

        // Get the last word of the message
        // If there is only one word, first and last will be the same
        String lastWord = words[words.length - 1];

        // Build the hash by combining all parts
        String hash = firstTwo + ":" + lastTwo + ":" + firstWord + lastWord;

        // Convert entire hash to uppercase and return it
        return hash.toUpperCase();
    }

    
   
    public String SentMessage(int choice) {
        // Check which option the user selected
        switch (choice) {

            case 1:
                // User chose to SEND the message
                totalMessagesSent++;        // Increase sent count
                sentMessages.add(messageText);  // Add to sent list
                messageHashes.add(messageHash); // Store the hash
                messageIDs.add(messageID);      // Store the ID
                recipients.add(recipientCell);  // Store recipient
                return "Message successfully sent.";

            case 2:
                // User chose to DISREGARD the message
                // Message is deleted and not saved anywhere
                return "Message successfully disregarded.";

            case 3:
                // User chose to STORE the message for later
                storedMessages.add(messageText); // Add to stored list
                messageHashes.add(messageHash);  // Store the hash
                messageIDs.add(messageID);       // Store the ID
                recipients.add(recipientCell);   // Store recipient
                return "Message successfully stored.";

            default:
                // User entered an invalid option
                return "Invalid option. Please choose 1, 2, or 3.";
        }
    }

   
    
    public void printMessages() {
        // Check if any messages have been sent
        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return; // Exit the method early
        }

        // Print a header
        System.out.println("\n===== SENT MESSAGES =====");

        // Loop through all sent messages and print each one
        for (int i = 0; i < sentMessages.size(); i++) {
            System.out.println("\nMessage " + (i + 1) + ":");
            System.out.println("Message ID   : " + messageIDs.get(i));
            System.out.println("Message Hash : " + messageHashes.get(i));
            System.out.println("Recipient    : " + recipients.get(i));
            System.out.println("Message      : " + sentMessages.get(i));
            System.out.println("-------------------------");
        }
    }

   
    public int returnTotalMessages() {
        // Return the static counter that tracks sent messages
        return totalMessagesSent;
    }

    
    
    public void storeMessage() {
        // Add the message to the stored messages list
        storedMessages.add(messageText);
        System.out.println("Message stored successfully.");
    }

   
    // Returns the message ID
    public String getMessageID()   { return messageID; }

    // Returns the message hash
    public String getMessageHash() { return messageHash; }

    // Returns the recipient cell number
    public String getRecipientCell() { return recipientCell; }

    // Returns the message text
    public String getMessageText() { return messageText; }

    // Returns the full list of sent messages
    public static ArrayList<String> getSentMessages() { return sentMessages; }

    // Returns the full list of stored messages
    public static ArrayList<String> getStoredMessages() { return storedMessages; }
}