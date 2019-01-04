// This is the code for the Player class. We need our player to have
// a few attributes:
// 1) The player's hand. This will be represented as a 12-element long array of integers.
//    It is 12 long because there are 12 possible types cards in deck, and each index
//    in the array measures how many cards of that type the player has.
// 3) The cards they've played, face down. This is very similar to the hand array, except
//    it is length 10. The reason it is length 10 and not 12 is that once a Sushi roll
//    card (1, 2, 3) is played, 
// 4) Their beliefs. This is represented by some array internal to
//    the player, representing what they know about the deck
//    of cards. Their beliefs are a function of the starting
//    distribution of the deck, their current hand, all of the
//    hands they've had before, and the cards they have, facedown.
public class Player
{
   int[] table = new int[10];
   int[] hand = new int[12];
   int score;
}