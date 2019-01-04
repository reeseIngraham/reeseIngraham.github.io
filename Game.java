// This file instantiates the game, drawing from Player.java and 
// Card.java. 

import java.util.Random;

public class Game
{
   int numRound = 1;
   // Let's create the universal deck.
   int[] deck = new int[12]; 
   
   // We also need some stochasticity in our simulation.
   Random r = new Random();
   
   // Let's also instantiate our players.
   Player a = new Player();
   Player b = new Player();
   
   public static void main(String [] args)
   {
      // Let's now fill the universal deck.
      fillDeck( deck );
      
      // And now, let's deal cards to each of our players.
      dealCards( a, b, deck ); 
      
      for( int numRound = 1; numRound == 10; numRound++)
      {
         tableUpdate( choice( a, b ), a);
         tableUpdate( choice( b, a ), b);
         switchHands( a, b );
      }
      
      scoreUpdate( a, b );
      if (a.score > b.score)
      {
         System.out.println("Player a wins!");
      }
      
      else if(a.score == b.score)
      {
         System.out.println("It's a dead-tie!");
      }
      
      else
      {
         System.out.println("Player b wins!");
      }

   }
     
   public void fillDeck( int[] deck )
   {
      // Originally, I just had this in the main function,
      // but for the purposes of clean code I made it its own
      // method. Here, I instantiate an array that serves as the deck.
      // Each index in the array accounts for a different card type.
      // Here's the guide for which index represents which card. Throughout
      // the project, these integers will represent whichever card is shown
      // according to the guide below.
      // 0 = Tempura
      // 1 = Sashimi
      // 2 = Dumpling
      // 3 = Sushi Roll
      // 4 = Salmon Nigri
      // 5 = Squid Nigri
      // 6 = Egg Nigri
      // 7 = Pudding
      // 8 = Wasabi
      // 9 = Chopsticks
      // Now, we can fill the deck in accordance with the game rules.
      deck[0] = 14;
      deck[1] = 14;
      deck[2] = 14;
      deck[3] = 12;
      deck[4] = 8;
      deck[5] = 5;
      deck[6] = 10;
      deck[7] = 5;
      deck[8] = 5;
      deck[9] = 10;
      deck[10] = 6;
      deck[11] = 4;
   }
   
   public void dealCards( Player me, Player you, int[] uDeck )
   {
      // The way this works is by essentially constructing a number line between
      // 0 and the total number of cards in the deck - 1. The algorithm then
      // partitions the number line into twelve different sections, each section
      // designated to a possible card. The size of each section is proportional
      // to the amount of cards of that type in the deck. It then 
      int dealRandom;
      int deckSum = 0;
      
      for(int i = 0; i < 10; i++)
      {
         // This just checks to see how many cards are in the deck.
         for(int j = 0; j < uDeck.length; j++)
         {
            deckSum += uDeck[j];
         }
         
         dealRandom = r.nextInt(deckSum - 0) + 0;
         
         if( ( 0 =< dealRandom) && (dealRandom =< uDeck[0])
         {
            me.hand[0] += 1;
            uDeck[0] -= 1;
         }
         
         else if( ( ( uDeck[0] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] ) ) )
         {
            me.hand[1] += 1;
            uDeck[1] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] ) ) )
         {
            me.hand[2] += 1;
            uDeck[2] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] ) ) )
         {
            me.hand[3] += 1;
            uDeck[3] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] ) ) )
         {
            me.hand[4] += 1;
            uDeck[4] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] ) ) )
         {
            me.hand[5] += 1;
            uDeck[5] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] ) ) )
         {
            me.hand[6] += 1;
            uDeck[6] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] ) ) )
         {
            me.hand[7] += 1;
            uDeck[7] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] ) ) )
         {
            me.hand[8] += 1;
            uDeck[8] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] ) ) )
         {
            me.hand[9] += 1;
            uDeck[9] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] ) ) )
         {
            me.hand[10] += 1;
            uDeck[10] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] ) ) )
         {
            me.hand[11] += 1;
            uDeck[11] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] + uDeck[12] ) ) )
         {
            me.hand[11] += 1;
            uDeck[11] -= 1;
         }
         
         else
         {
            System.out.println("Error!");
         }
         
         
         // Now for j's hand.
         dealRandom = r.nextInt(deckSum - 0) + 0;
         if( ( 0 =< dealRandom) && (dealRandom =< uDeck[0])
         {
            you.hand[0] += 1;
            uDeck[0] -= 1;
         }
         
         else if( ( ( uDeck[0] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] ) ) )
         {
            you.hand[1] += 1;
            uDeck[1] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] ) ) )
         {
            you.hand[2] += 1;
            uDeck[2] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] ) ) )
         {
            you.hand[3] += 1;
            uDeck[3] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] ) ) )
         {
            you.hand[4] += 1;
            uDeck[4] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] ) ) )
         {
            you.hand[5] += 1;
            uDeck[5] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] ) ) )
         {
            you.hand[6] += 1;
            uDeck[6] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] ) ) )
         {
            you.hand[7] += 1;
            uDeck[7] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] ) ) )
         {
            you.hand[8] += 1;
            uDeck[8] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] ) ) )
         {
            you.hand[9] += 1;
            uDeck[9] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] ) ) )
         {
            you.hand[10] += 1;
            uDeck[10] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] ) ) )
         {
            you.hand[11] += 1;
            uDeck[11] -= 1;
         }
         
         else if( ( ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[4] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] + 1 ) =< dealRandom ) && ( dealRandom =< ( uDeck[0] + uDeck[1] + uDeck[2] + uDeck[3] + uDeck[5] + uDeck[6] + uDeck[7] + uDeck[8] + uDeck[9] + uDeck[10] + uDeck[11] + uDeck[12] ) ) )
         {
            you.hand[11] += 1;
            uDeck[11] -= 1;
         }
         
         else
         {
            System.out.println("Error!");
         }
      }
   }
   
   // This method helps each player calculate their choice by performing intermediate
   // valuation functions on each of their possible choice. The choice the player
   // will make maximizes their payoffs in that round.
   public int intermediateValuation( int cardChoice, Player me, Player you)
   {
      if( cardChoice == 0 ) // Tempura
      {
         if( ( ( me.table[0] + 1 ) % 2 ) == 0)
         {
            intermediateValuation = 0;
         }
            
         else
         {
            intermediateValuation = 5;
         }
      }
         
      else if( cardChoice == 1 ) // Sashimi
      {
         if( ( ( me.table[1] + 1 ) % 3 ) == 0 );
         {
            intermediateValuation = 0;
         }
            
         else
         {
            intermediateValuation = 10;
         }
      }
         
      else if( cardChoice == 2 ) // Dumplings
      {
         if( ( me.table[2] + 1 ) == 1 )
         {
            intermediateValuation = 1;
         }
            
         else if( ( me.table[2] + 1) == 2 )
         {
            intermediateValuation = 3;
         }
            
         else if( ( me.table[2] + 1) == 3 )
         {
            intermediateValuation = 6;
         }
            
         else if( ( me.table[3] + 1) == 4 )
         {
            intermediateValuation = 10;
         }
            
         else
         {
            intermediateValuation = 0;
         }
      }
         
      else if(cardChoice == 3) // 1 Sushi Roll
      {
         if( ( me.table[3] + 1 ) > you.table[3] )
         {
            intermediateValuation = 6;
         }
            
         else
         {
            intermediateValuation = 3;
         }
      }
         
      else if(cardChoice == 4) // 2 Sushi Roll
      {
         if( ( me.table[3] + 2 ) > you.table[3] )
         {
            intermediateValuation = 6;
         }
            
         else
         {
            intermediateValuation = 3;
         }
      }
         
      else if(cardChoice == 5) // 3 Sushi Roll
      {
         if( ( me.table[3] + 3 ) > you.table[3] )
         {
            intermediateValuation = 6;
         }
            
         else
         {
            intermediateValuation = 3;
         }
      }
         
      else if(cardChoice == 6) // Salmon Nigiri
      {
         if( ( ( me.table[4] + 1 + me.table[9] ) % 2 ) == 0 )
         {
            intermediateValuation = 6;
         }
            
         else
         {
            intermediateValuation = 2;
         }
      }
         
      else if(cardChoice == 7) // Squid Nigiri
      {
         if ( ( ( me.table[5] + 1 + me.table[9] ) % 2 ) == 0 )
         {
            intermediateValuation = 9;
         }
            
         else
         {
            intermediateValuation = 3;
         }
      }
         
      else if(cardChoice == 8) // Egg Nigiri
      {
         if ( ( ( me.table[6] + 1 + me.table[9] ) ) % 2 ) == 0
         {
            intermediateValuation = 3;
         }
            
         else
         {
            intermediateValuation = 1;
         }
      }
         
      else if(cardChoice == 9) // Pudding
      {
         if ( me.table[7] > you.table[7] )
         {
            intermediateValuation = 6;
         }
            
         else if ( me.table[7] == you.table[7] )
         {
            intermediateValuation = 3;
         }
            
         else
         {
            intermediateValuation = -6;
         }
      }
         
      else if(cardChoice == 10) // Wasabi
      {
         intermediateValuation = 0;
      }
         
      else if(cardChoice == 11)
      {
         intermediateValuation = 0;
      }
   }
   public int choice( Player me, Player you )
   {
      int[][] strategySet = new int[2][11 - numRound]
      // I'm creating an arrayList of the action set of the player. Later in this method,
      // I determine the player's choice by sorting the arrayList by the intermediate valuation
      // function, and then whatever is at the top of the arrayList must have the highest 
      // intermediate value, and thus should be the player's choice. Thus, the player
      // discounts the future heavily (i.e. doesn't value future turns at all, which makes
      // this algorithm a greedy one.
      
      for( int i = 0; i < me.hand.length; i++ )
      {
         if (me.hand[i] == 0)
         {
            continue;
         }
         
         else
         {
            strategySet[0][i] = i;
            strategySet[1][i] = intermediateValuation( i, a.table, b.table )
         }
      }
      
      // Now, we're going to do bubble sort on our strategySet array.
      for (int i = 0; i < strategySet[0].length - 1; i++)
      {
         for (int j = 0; j < strategySet[0].length - i - 1; j ++)
         {
            if(strategySet[1][j] > strategySet[1][j])
            {
               int tempCard = strategySet[0][j]
               int tempIV = strategySet[1][j];
               strategySet[0][j] = strategySet[0]j + 10];
               strategySet[j][1] = strategySet[j + 1][1];
               strategySet[0][j + 1] = tempCard;
               strategySet[1][j + 1] = tempIV;
            }
         }
      }
      
      // Now that we've sorted the array, the value that we want should be
      // the last value of the array.
      choice = strategySet[strategySet[0].length - 1];
   }
      
   public void switchHands( Player me, Player you )
   {
      // In order to switch, we need to make a deep copy of one of the 
      // hand arrays.
      int[] mehandCopy = new int[10];
      for(int i = 0; i < me.hand.length; i++)
      {
         mehandCopy[i] = me.hand[i];
      }
      
      // Now, let's make the switch from me.hand to you.hand.
      for(int j = 0; j < me.hand.length; j++)
      {
         me.hand[j] = you.hand[j];
      }
      
      // Finally, let's make the switch from you.hand to me.hand.
      for(int k = 0; k < me.hand.length; k++)
      {
         you.hand[k] = mehandCopy[k];
      }
   }
   
   public void tableUpdate( int card, Player me)
   {
      me.table[card] += 1;
   }
   
   public int scoreUpdate( Player me, Player you)
   {
      for( int i = 0; i < me.table.length; i++)
      {
         me.score += intermediateValuation(me.table[i], me, you);
         you.score += intermediateValuation(you.table[i], you, me);
      }
   }
}