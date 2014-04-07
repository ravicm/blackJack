BlackJack
=========

Text-based blackJack game

The game is designed using the rules descibed from the following references:

  * http://en.wikipedia.org/wiki/Blackjack
  * http://www.blackjackinfo.com/blackjack-rules.php

It is pretty clear from above references that the play is conducted in several variations. The code assumes most generic variation in all. Hence, the following are to be noted:

- Dealer stands on all 17's including soft 17.
- Split allowed only once
- Doubling is not allowed after Split.

##How to play:
-----------

You can choose to play the game by answering Yes/No at command prompt. However, a simulation is already included in the code that answers Yes/No based on chosen playing strategy.

The questions are as follows:
-  Are you ready to start the game?
-  Do you want to double down?
-  Hit(y)/Stand(n)
(Process repeats)

##Overview of the code:
---------


###The code is divided into four packages:

####I. Extensible: 

This contains  interfaces to extension points of the game. A sample class has been provided and used. You can choose to extend the game by implementing the provided interfaces. A brief description of extenstion points is given below:

**1. Playing strategy (Interface: Simulator):** Given a hand and one card of dealer. You can plan to take a hit or stand by implementing Simulator to say Yes/No for hit/stand. 
Sample simulators included are: 

  1. Random simulator (Class: RandomSimulator) choses Hit/Stand randomly.  
  2. Smart simulator (Class: SmartSimulator) is not completely implemented but the idea is to let you decide intellegintly based on hand and dealer card.

**2. Payout Ratio (Interface: PaymentRatio):** Some casinos pay 3:2 if player gets blackjack some dont. Some casinos pay 1:2 if dealer is busted. You can implement the interface PaymentRatio. Sample PaymentRatio's implemented is SimplePaymentRatio.

**3. Betting strategy (Interface: BettingStrategy):** Player has to bet atleast one chip each hand. The exact amount for each hand has to declared before receiving cards. This amount can be randomly chosen (Class: RandomBettingStrategy) or simply bet 10 chips every time (Class: SimpleBettingStrategy) or follow martingale approach (Class: MartingaleBettingStrategy).
				

####I. Participants:

Two kinds of participants (package: participants) - Dealer and a Player. A good number of attributes are custimizable. Player's initial amount, betting strategy, Dealer's Payout ratio can be set in the Game.java.


####III. Resources: 

Table, Deck (/Shoe) and Cards are the resources (package: resources). Table has a customizable Dealer to which a customizable player can walk in to play the fame.


####IV: Util:

Contains utilities required for the game. The main idea behind this to keep it expandable. However, they are not the extenstion points for the game. For example: print is used to print to System.out but more advanced packages like logger can be used to further enhance the code.

One line description of the included classes:
----
```
src
  gameController 
    Game.java  - Starting point for execution of the game
			
  resources 
    Card.java  - Describe the real world Card of deck. Each deck has real value and blackjack face value.
    Deck.java  - Deck of 52 cards
    Table.java - Contains dealer, shoe(/deck)
			
  extensible (Described above)
    BettingStrategy.java 
    RandomBettingStrategy.java 
    SimpleBettingStrategy.java 
    MartingaleBettingStrategy.java 
      
    Simulator.java 
    SmartSimulator.java 
    RandomSimulator.java 
      
    PaymentRatio.java 
    SimplePaymentRatio.java 
      
  util 
    utilities.java - Print info output and other utilities
			
  participants 
    actions.java - Interface to define actions to be known by every participant
    Player.java - A customisable player
    Participant.java - Particpant, extended to player or dealer
    Dealer.java - A customisable Dealer 
```
