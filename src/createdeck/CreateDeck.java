/*
 *       ___           ___           __            ___           ___           ___       ___ 
 *      /  /\         /  /\         |  |\         /  /\         /  /\         /  /\     /  /\
 *     /  /::|       /  /::\        |  |:|       /  /:/_       /  /::\       /  /:/    /  /:/
 *    /  /:|:|      /  /:/\:\       |  |:|      /  /:/ /\     /  /:/\:\     /  /:/    /  /:/ 
 *   /  /:/|:|__   /  /::\ \:\      |__|:|__   /  /:/ /:/_   /  /::\ \:\   /  /:/    /  /:/  
 *  /__/:/_|::::\ /__/:/\:\_\:\ ____/__/::::\ /__/:/ /:/ /\ /__/:/\:\ \:\ /__/:/    /__/:/   
 *  \__\/  /~~/:/ \__\/  \:\/:/ \__\::::/~~~~ \  \:\/:/ /:/ \  \:\ \:\_\/ \  \:\    \  \:\   
 *        /  /:/       \__\::/     |~~|:|      \  \::/ /:/   \  \:\ \:\    \  \:\    \  \:\  
 *       /  /:/        /  /:/      |  |:|       \  \:\/:/     \  \:\_\/     \  \:\    \  \:\ 
 *      /__/:/        /__/:/       |__|:|        \  \::/       \  \:\        \  \:\    \  \:\
 *      \__\/         \__\/         \__\|         \__\/         \__\/         \__\/     \__\/
 * 
 */
package createdeck;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Maxwell Ortega
 */
public class CreateDeck extends Application {
    
    
    class Node{
		int data;
		int index; 
		String color; 
		Node next, prev; 
		
		public Node (int data, int index, String color) {
			this.data = data; 
			this.index = index; 
			this.color = color;
			this.next = null;
			this.prev = null; 
			}
		}
	class Sent{
		String sentence; 
		Sent next; 
		Sent prev; 
		public Sent(String sentence) {
			this.sentence = sentence; 
		}
			
	}
	
	public Node head = null; 
	public Node tail = null; 
	public Sent text_head = null; 
	public Sent text_tail = null; 
	
	public static CreateDeck list = new CreateDeck(); 
	public static CreateDeck hand = new CreateDeck();
	public static CreateDeck pen = new CreateDeck();
        public static int[] TOTALSTATS = {0,0,0,0,0};//keeps track of total stats
        public static String punishment[] = {"Push Ups", "Squat", "Sit Ups", "Lunges", "Burpees"};//change this to change workouts
	
        //All good
	public void addNode(int data, int index, String color) {
		
		Node newNode = new Node(data, index, color);
		
		if (head == null) {
			head = newNode; 
			tail = newNode; 
		}
		else {
			tail.next = newNode; 
			
			tail = newNode;
		}
	}

	//All good
	public void create_sentence(String sentence) {
		Sent newText = new Sent(sentence); 
		
		if (text_head == null) {
			text_head = newText; 
			text_tail = newText; 
		}
		else {
			text_tail.next = newText; 
			text_tail = newText;
		}
	}
	
        //All good
	public void display() {
		Node current = head ; 
		String above[] = {"Draw 4", "Wild", "Reverse", "Draw 2", "Skip"};
		String final_hand = ""; 
		if (current == null) {
			System.out.println("Deck is empty.\n");
			return; 
		}
                
		while (current != null) {
                    if(current.next == null){
                        if(current.data > 9) {
				String card = above[14-current.data] + " of " + current.color + " ";
				final_hand = final_hand + card; 
			}
			else {
				String card = current.data + " of " + current.color + " ";
				final_hand = final_hand + card;
			}
                    }
                    else{
			if(current.data > 9) {
				String card = above[14-current.data] + " of " + current.color + ", ";
				final_hand = final_hand + card; 
			}
			else {
				String card = current.data + " of " + current.color + ", ";
				final_hand = final_hand + card;
			}
                    }
			current = current.next; 
		}
                pen.create_sentence(final_hand);
	}
	
	//All good
	public void shuffle(int deck_count) {
		Random r = new Random();
		Node xyz = head; 
		int temp, cards_in_the_deck = deck_count *108; 
		String change_color; 
		
		for (int i = 0; i < cards_in_the_deck; i++) {
			temp = xyz.data;
			change_color = xyz.color; 
			
			int index = r.nextInt((cards_in_the_deck-0)+1);
			Node current = head; 
			while(current!= null) { 
				if (current.index == index) {
					int x = current.data; 
					String y = current.color; 
					current.data = temp;
					current.color = change_color; 
					xyz.data = x;
					xyz.color = y; 
				}
				current = current.next; 
			}
			if(xyz.next==null)
				break;
			xyz = xyz.next; 
		}
	}
	
        //All good
	public void create_deck(int deck_count, boolean action_cards) {
		
            if(action_cards == true){
		int cards_present = deck_count *108; 
		int index = 0, card_count = 13; 
		String color = "";
		for (int j = 0; j< deck_count; j++) {
			int count = 1; 
			while(count!= 5) {
				if (count == 1)
					color = "Blue"; 
				else if (count == 2)
					color = "Red"; 
				else if (count == 3)
					color = "Yellow"; 
				else
					color = "Green"; 
				for (int i = 0; i< card_count; i++) {
					 if (i==0) {
						 addNode(0, index, color);
						 index = index+1; 
					}
					 else if (i>=1 && i<=12) {
						 	addNode(i, index, color);
						 	addNode(i, index+1, color);
						 	index = index+2; 
						 }
					 }
					count = count +1; 
				}
				for(int y = 0; y<8; y++) {
					//Adding wild card and +4 to the stack.
					if(y>=0 &&y<4) {
						addNode(13, index, "Wild");
						index = index+1; 
					}
					else {
						addNode(14, index, "Wild");
						index = index+1; 
					}
						
				}
			}
            }
            else if(action_cards == false){
                int cards_present = deck_count *76; 
		int index = 0, card_count = 10; 
		String color = "";
		for (int j = 0; j< deck_count; j++) {
			int count = 1; 
			while(count!= 5) {
				if (count == 1)
					color = "Blue"; 
				else if (count == 2)
					color = "Red"; 
				else if (count == 3)
					color = "Yellow"; 
				else
					color = "Green"; 
				for (int i = 0; i< card_count; i++) {
					 if (i==0) {
						 addNode(0, index, color);
						 index = index+1; 
					}
					 else if (i>=1 && i<=9) {
						 	addNode(i, index, color);
						 	addNode(i, index+1, color);
						 	index = index+2; 
						 }
					 }
					count = count +1; 
				}
			}
            }
	}
	
        //All good
	public static int findIndex(String arr[], String t){ 
        if (arr == null) { 
            return -1; 
        } 

        int len = arr.length; 
        int i = 0; 
 
        while (i < len) { 
            if (arr[i] == t) { 
                return i; 
            } 
            else { 
                i += 1; 
            } 
        } 
        return -1; 
    } 

        //All good
	public void sortBubble(String check_color[]) {
		Node current; 
		for (int i = 0 ;i <7; i++) { // loops until an individual gets seven cards
			current = head; 
			for (int j = 0; j < 6; j++){
				if (current!= null && current.next != null && findIndex(check_color, current.color) < findIndex(check_color, current.next.color)) { //checks the color of the card distributed
					int temp_data = current.data;
					String temp_color = current.color; 
					int temp_index = i; 	//current.index; 
					current.data = current.next.data; 
					current.color = current.next.color; 
					current.index = current.next.index; 
					current.next.data = temp_data; 
					current.next.color = temp_color; 
					current.next.index = temp_index; 
				}
				if (current!=null)
					current = current.next; 
			}
		}
	}
	
        //All good
        public void sortRank() {
		Node current; 
		for (int i = 0 ;i <7; i++) {  // cards are ranked in ascending order
			current = head;
                        if(current != null){
                            for (int j = 0; j < 6; j++){
                                if(current != null && current.next != null){
                                    if ((current.data > current.next.data) && (current.color == current.next.color)) {
                                            int temp_data = current.data;
                                            current.data = current.next.data; 
                                            current.next.data = temp_data; 
                                    }
                                    current = current.next;
                                }
                            }
                        }
		}
	}
	
        //All good
	public void display_text() throws FileNotFoundException{
		try {
                    
			File file = new File("UnoWorkout.html"); 
			Writer output = new BufferedWriter(new FileWriter(file));
			 
			output.write("<html class=\"UNO GAME OUTPUT MODEL\" lang=\"en-us\">\n" + "<head>\n" + "   <title>UNO GAME MODEL | GAME OUTPUT </title>\n" +
                    "</head>\n" +" <body>\n" +"<h1>UNO WORKOUT | GAME OUTPUT</h1>\n"+
                    "<p><img src=\"https://github.com/MaxwellOrtega/Project-2-UNO-Workout-/blob/main/Uno-890x500.png?raw=true\" alt=\"Workouts\" width=\"500\" height=\"500\" style=\"float:right; margin: 0 0 10px 10px;\" /></p>\n" +                  
                    "<h2> UNO GAME</h2>\n");
			
			output.write("<html>"); 
			output.write("<head>"); 
			output.write("<title>"); 
			output.write("Uno Game in Html </title>"); 
			output.write("</head>"); 
			output.write("<body>"); 
			
                        Sent current = text_head;
			while(current!=null) {
                            String table = "<p>"+current.sentence+"</p>";
                            output.write(table);
                            current = current.next;
                        }
			output.write("</body>"); 
			output.write("</html>"); 
                        
                        output.write("TOTALS: \n");
                        for(int stats = 0; stats < 5; stats++){
                            output.write(punishment[stats] + ": " + TOTALSTATS[stats] + "\t");
                        }
                        
			output.close();
		}catch (IOException e) {
			System.out.println("Couldn't find any file.\n");
		}
		
	}
	
	//All good
	public void play_cards(){
		Node current = head; 
		if (current == null )
		System.out.println("Current is null");
		String above[] = {"Draw 4", "Wild", "Reverse", "Draw 2", "Skip"};
		int exerciseCount[] = {0,0,0,0,0}; //Last element for the burpees  
		String suits[] = {"Blue", "Yellow", "Red", "Green", "Wild"}; 
		String punishment[] = {"Push Ups", "Squat", "Sit Ups", "Lunges", "Burpees"};
		int present = 0;
                
		while(current!=null ){
			for (int i = 0; i<5; i++) {

				if (i < 5 && current.data <= 9 && current.color == suits[i] ) {
					exerciseCount[i] = exerciseCount[i] + current.data;
					if (current.data == 0)
						pen.create_sentence("0 card delt, +1 minute break at end of round.");
					i++;
				}
				else if (current.data > 9 && current.color == suits[i]) {
                                    int remainder = 14-current.data;

                                    if (remainder == 3) {//Draw 2
                                        exerciseCount[i] *= 2;
                                    }
                                    if (remainder == 0){//Draw 4
                                            exerciseCount[4] += 4;
                                            present = 1;
                                    } 
                                    if(remainder == 4){//Skip
                                        int num = findIndex(suits, current.color);
                                        exerciseCount[num] = 0;
                                    }
                                    if (remainder == 2) {//Reverse
                                            int count = 0, add; 

                                            add = findIndex(suits, current.color); 
                                            exerciseCount[add] = 0; 
                                            while( i< 4 && suits[i] == current.color && current.prev!=null) {
                                                    current = current.prev;
                                                    count++; 
                                            }
                                            for (int c = 0; c <= count; c++) {
                                                    if (current.next != null && current.data <=9) {
                                                            list.addNode(current.data, current.index, current.color);	
                                                            current = current.next; 
                                                    }
                                            }

                                    }
                                    if(remainder == 1){//for wild
                                        exerciseCount[4] += 4;
                                    }
						
				}
						
			}
			current = current.next; 
		}
                
		int total = 0; 
		String paper = "";
                
		for (int x = 0; x<5; x++) {
			total = exerciseCount[x];
                        if(x == 4){
                            paper = suits[x] + ": " + (total) + " "+ punishment[x] + " for this round.";
                            TOTALSTATS[x] += total;
                            pen.create_sentence(paper);
                        }
                        else if (exerciseCount[x] > 0) {
				if (present == 1 ) {
                                    paper  = suits[x] + ": " + (total * 4) + " " + punishment[x] + " for this round.";
                                    TOTALSTATS[x] += total * 4;
                                }
                                else{
                                    paper = suits[x] + ": " + (total) + " "+punishment[x] + " for this round.";
                                    TOTALSTATS[x] += total;
                                }
                                
				 
				pen.create_sentence(paper);
			}
			else {
				paper = suits[x] + ": " + "No "+ punishment[x] +" for this round.";
				pen.create_sentence(paper);
			}
		}
                
	}
	
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        
        Scanner scanf = new Scanner(System.in); 
        launch(args);
        int deck_count;

        //Loop to read all the cards
        System.out.println("How many decks do you want to use?\n"); 
        deck_count = scanf.nextInt(); 
        list.create_deck(deck_count, true);
        int shuf_deck = 0;
        int deal_hand = 1;
        int show_list = 0;
        
        System.out.println("Do you want to view the deck?(1 or 0)\n");
        show_list = scanf.nextInt();
        if (show_list == 1) {

                list.display();
                pen.display_text();
        }

        System.out.println("Do you want to shuffle the deck?(1 or 0)\n");
        shuf_deck = scanf.nextInt();
        if (shuf_deck == 1) {
                list.shuffle(deck_count);
                System.out.println("Deck has been Shuffled\n");
        }

        //Loop for all cards
        while(list.head!= null && shuf_deck>=0) {

            System.out.println("Type 1 to deal the next hand, Type -1 to exit.\n");
            deal_hand = scanf.nextInt();
            if (deal_hand == -1)
                    break; 

            //Creating a hand for a round
            int end = 1; 
            while(list.head!=null && end <= 7) {
                    hand.addNode(list.head.data, list.head.index, list.head.color);
                    end = end +1; 
                    list.head = list.head.next;
            }


            String color_prec[] = {"+4","Wild","Green", "Red", "Yellow", "Blue"}; 
            hand.sortBubble(color_prec);
            hand.sortRank();

            hand.display(); 
            hand.play_cards();
            //System.out.println("\n\n");
            //hand.display();

            hand.head = hand.tail = null; 

            pen.display_text();
            System.out.println();
            if(list.head!=null)
                    list.head = list.head.next;			
        }
    }
}
