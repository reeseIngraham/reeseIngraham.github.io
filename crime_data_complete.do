// 
insheet using crimeDataDirty.csv, name
save crimeDataClean.dta, replace
log using crimeDataComplete.log, replace
// Here, I drop all of the variables that were included in the data set
// that I won't be using.
drop premisecode
drop premisedescription
drop weapondescription
drop statuscode
drop location
drop crossstreet
drop address
drop mocodes
drop crimecode4
drop crimecode3
drop statusdescription
drop crimecode1
drop crimecode2
drop timeoccurred
drop areaname
drop reportingdistrict
sort crimecode

// "Drunk roll"? Not even UrbanDictionary could
// help me out with that one.
drop if crimecode == 353
drop if crimecode == 453

// "Document worthless" - vague
drop if crimecode == 651
drop if crimecode == 652

// Here, I drop observations that had no recorded value for 
// crimecodedescription.
drop if crimecode == 760
drop if crimecode == 814
drop if crimecode == 822
drop if crimecode == 921

// "Crime against child" is vague
drop if crimecode == 812

// "Other miscellaneous crime"? Vague
drop if crimecode == 946 

// "Illegal dumping" is an LA municipal code, not California code, so we'll
// drop it
drop if crimecode == 949

// "Illegal abortion" is a LA municipal code, not CA code, so we'll drop this
// one too
drop if crimecode == 952

// Now, we're making a new dummy variable violent s.t. violent = 1 iff the
// crime necessitates the application of force on the victim, else violent = 0
generate violent = 0
replace violent = 1 if crimecode == 110
replace violent = 1 if crimecode == 113
replace violent = 1 if crimecode == 121
replace violent = 1 if crimecode == 122
replace violent = 1 if crimecode == 210
replace violent = 1 if crimecode == 220
replace violent = 1 if crimecode == 230
replace violent = 1 if crimecode == 231
replace violent = 1 if crimecode == 235
replace violent = 1 if crimecode == 236
replace violent = 1 if crimecode == 250
replace violent = 1 if crimecode == 251
replace violent = 1 if crimecode == 435
replace violent = 1 if crimecode == 436
replace violent = 1 if crimecode == 622
replace violent = 1 if crimecode == 623
replace violent = 1 if crimecode == 624
replace violent = 1 if crimecode == 625
replace violent = 1 if crimecode == 626
replace violent = 1 if crimecode == 627
replace violent = 1 if crimecode == 647
replace violent = 1 if crimecode == 648
replace violent = 1 if crimecode == 753
replace violent = 1 if crimecode == 761
replace violent = 1 if crimecode == 815
replace violent = 1 if crimecode == 820
replace violent = 1 if crimecode == 821
replace violent = 1 if crimecode == 860
replace violent = 1 if crimecode == 882
replace violent = 1 if crimecode == 910
replace violent = 1 if crimecode == 920
replace violent = 1 if crimecode == 922
// Here, I generate a new variable, perCapitaCrime.
// Using the LA Times' LA Mapping Project (source in bibliography), I used
// the per capita crime for the area of Los Angeles that
// the reporting police station resides in. Not perfect, but good enough 
// for our purposes.
generate perCapitaCrime = .
replace perCapitaCrime = 46.0 if areaid == 1
replace perCapitaCrime = 45.9 if areaid == 2
replace perCapitaCrime = 121.8 if areaid == 3
replace perCapitaCrime = 32.8 if areaid == 4
replace perCapitaCrime = 24.0 if areaid == 5
replace perCapitaCrime = 72.3 if areaid == 6
replace perCapitaCrime = 23.5 if areaid == 7
replace perCapitaCrime = 18.4 if areaid == 8
replace perCapitaCrime = 30.5 if areaid == 9
replace perCapitaCrime = 23.6 if areaid == 10
replace perCapitaCrime = 17.1 if areaid == 11
replace perCapitaCrime = 71.8 if areaid == 12
replace perCapitaCrime = 46.0 if areaid == 13
replace perCapitaCrime = 18.4 if areaid == 14
replace perCapitaCrime = 19.4 if areaid == 15
replace perCapitaCrime = 24.1 if areaid == 16
replace perCapitaCrime = 30.5 if areaid == 17
replace perCapitaCrime = 32.6 if areaid == 18
replace perCapitaCrime = 26.4 if areaid == 19
replace perCapitaCrime = 45.2 if areaid == 20
replace perCapitaCrime = 37.8 if areaid == 21
// weaponusedcode is a variable included in the dataset which give the police
// code of any weapon(s) used in the crime, if there was one. I make the
// assumption that the data was correctly entered and that a null value
// actually means that there was no weapon involved, and not just that the
// officer reporting forgot to enter in a value, thus setting weaponUsed = 0
// iff weaponusedcode is null and weaponUsed = 1 if weaponusedcode isn't null.
generate weaponUsed = .
replace weaponUsed = 0 if weaponusedcode == .
replace weaponUsed = 1 if weaponusedcode != .
drop weaponusedcode
// Here, I'm converting the date reported and occurred into a date format
// that Stata can read and do calculations on.
generate tdreport = date(datereported, "MDY")
format tdreport %td
generate tdoccur = date(dateoccurred, "MDY")
format tdreport %8.0g
generate dateDifference = tdreport - tdoccur
// Here, I drop more observations that have missing values.
drop if victimage == .
drop if victimsex != "F" & victimsex != "M"
// In the below 3 lines, I turn the victimsex variable into a dummy variable
// female that Stata can read.
generate female = .
replace female = 1 if victimsex == "F"
replace female = 0 if victimsex == "M"
// I drop tdreport and tdoccur as I have already done the calculations I need 
// with them.
drop tdreport
drop tdoccur
// As a proxy for the severity of the crime, I generate variable minSent s.t.
// minSent = the minimum sentence for the crime. For more information, see
// section 3.
generate minSent = .
replace minSent = 25 if crimecode == 110 // Criminal homicide
replace minSent = 2 if crimecode == 113 // Negligent manslaughter
replace minSent = 3 if crimecode == 121 // Forcible rape
replace minSent = 1.5 if crimecode == 122 // Attempted rape
replace minSent = 3 if crimecode == 210 // Robbery
replace minSent = 1.5 if crimecode == 220 // Attempted robbery
replace minSent = 2 if crimecode == 230 // Assault with deadly weapon
replace minSent = 2 if crimecode == 231 // Assault with deadly weapon on police
// officer
replace minSent = 0 if crimecode == 235 // Physical child abuse
replace minSent = 5 if crimecode == 236 // Aggravated assault on intimate
// partner
replace minSent = 4 if crimecode == 237 // Child neglect
replace minSent = 0.5 if crimecode == 250 // Shots fired at moving vehicle
replace minSent = 0.5 if crimecode == 251 // Shots fired at inhabited dwelling
replace minSent = 2 if crimecode == 310 // Burglary
replace minSent = 1 if crimecode == 320 // Attempted burglary
replace minSent = 2 if crimecode == 330 // Burglary from vehicle
replace minSent = 0 if crimecode == 331 // Grand theft from vehicle over $400
replace minSent = 0 if crimecode == 341 // Grand theft from vehicle over $950
replace minSent = 0 if crimecode == 343 // Shoplifting
replace minSent = 0 if crimecode == 345 // Grand theft from dishonest employee
replace minSent = 0 if crimecode == 347 // Insurance fraud
replace minSent = 0 if crimecode == 349 // Grand theft by auto repair
replace minSent = 0 if crimecode == 350 // Theft from person
replace minSent = 0 if crimecode -- 351 // Purse snatching
replace minSent = 0 if crimecode == 420 // Petty theft
replace minSent = 0 if crimecode == 421 // Attempted petty theft
replace minSent = 0 if crimecode == 433 // Driving without owner's consent
replace minSent = 0 if crimecode == 434 // False imprisonment
replace minSent = 2 if crimecode == 435 // Lynching - in California Criminal
// Code, lynching (fortunately) doesn't mean hanging someone from a tree, 
// it means using a riot to take a person from the lawful custody of a 
// peace officer.
replace minSent = 1 if crimecode == 436 // Attempted lynching
replace minSent = 0 if crimecode == 437 // Resisting arrest
replace minSent = 0 if crimecode == 438 // Reckless driving
replace minSent = 0 if crimecode == 439 // False police Report
replace minSent = 0 if crimecode == 440 // Plain petty theft
replace minSent = 0 if crimecode == 441 // Attempted plain petty theft
replace minSent = 0 if crimecode == 442 // Shoplifting
replace minSent = 0 if crimecode == 443 // Attempted shoplifting 
replace minSent = 0 if crimecode == 444 // Theft by dishonest employee
replace minSent = 0 if crimecode == 445 // Attempted theft by dishonest employee
replace minSent = 0 if crimecode == 446 // Petty theft by auto repair
replace minSent = 0 if crimecode == 450 // Attempted petty theft by person
replace minSent = 0 if crimecode == 451 // Attempted purse snatching
replace minSent = 0 if crimecode == 452 // Attempted pickpocketing 
// Drop another drunk roll observation. Still not sure what drunk roll is. Is
// it like cow-tipping but you roll drunks down a hill? Do you race to see who
// can roll drunks the fastest?
drop if crimecodedescription == "Drunk Roll"
replace minSent = 0 if crimecode == 470 // Grand theft - till tap
replace minSent = 0 if crimecode == 471 // Petty theft - till tap
replace minSent = 0 if crimecode == 473 // Grand theft - coin machine
replace minSent = 0 if crimecode == 474 // Petty theft - coin machine
replace minSent = 0 if crimecode == 475 // Attempted theft - coin machine
replace minSent = 0 if crimecode == 480 // Stolen bike
replace minSent = 0 if crimecode == 485 // Attempted stolen bike
replace minSent = 0 if crimecode == 487 // Stolen boat
replace minSent = 0 if crimecode == 510 // Stolen vehicle
replace minSent = 0 if crimecode == 520 // Attempted stolen vehicle
replace minSent = 3 if crimecode == 622 // Battery on firefighter
replace minSent = 3 if crimecode == 623 // Battery on police officer
replace minSent = 0 if crimecode == 624 // Simple battery
replace minSent = 0 if crimecode == 625 // Other battery
replace minSent = 3 if crimecode == 626 // Domestic violence enhancement
replace minSent = 0 if crimecode == 627 // Physical child abuse
replace minSent = 0 if crimecode == 647 // Throwing object at moving vehicle
replace minSent = 2.66 if crimecode == 648 // Arson
replace minSent = 0 if crimecode == 649 // Document forgery
// Dropped "Document worthless" - really vague
drop if crimecode == 651
drop if crimecode == 652
replace minSent = 0 if crimecode == 653 // Credit card fraud
replace minSent = 0 if crimecode == 654 // Credit card fraud
replace minSent = 2 if crimecode == 660 // Counterfeiting
replace minSent = 0 if crimecode == 661 // Unauthorized computer access
replace minSent = 0 if crimecode == 662 // Grand theft by false pretense
replace minSent = 0 if crimecode == 664 // Petty theft by false pretense
replace minSent = 0 if crimecode == 666 // Attempted theft by false pretense 
replace minSent = 2 if crimecode == 668 // Grand theft by embezzlement
replace minSent = 2 if crimecode == 670 // Petty theft by embezzlement
replace minSent = 0 if crimecode == 740 // Vandalism doing over $400 of damage'
replace minSent = 0 if crimecode == 745 // Vandalism doing under $400 of damage
replace minSent = 0.49 if crimecode == 753 // Discharge firearm, technically the
// minimum sentence is 180 days, so 180 / 365 ~ 0.49
replace minSent = 0 if crimecode == 755 // Bomb threat
replace minSent = 0 if crimecode == 756 // Possession of bomb
replace minSent = 0.25 if crimecode == 761 // Brandishing weapon, minimum
// sentence is 90 days, and 90 / 365 ~ 0.25
replace minSent = 0 if crimecode == 762 // Lewd conduct
replace minSent = 0 if crimecode == 763 // Stalking
replace minSent = 3 if crimecode == 805 // Pimping
replace minSent = 3 if crimecode == 806 // Pandering
replace minSent = 0 if crimecode == 810 // Unlawful sex (Statutory rape)
drop if crimecode == 812 // "Crime against child" - vague
replace minSent = 0 if crimecode == 813 // Annoying a child
replace minSent = 3 if crimecode == 815 // Sexual penetration w/ foreign object
replace minSent = 0 if crimecode == 820 // Oral copulation
replace minSent = 0 if crimecode == 821 // Sodomy; This is forced sodomy, as in 
// 2003 the US Supreme Court struck down consensual sodomy laws as
// unconstitutional, and this data set only goes back to 2010
replace minSent = 1.33 if crimecode == 830 // Incest; technically, F
replace minSent = 0 if crimecode == 840 // Bestiality
replace minSent = 0 if crimecode == 850 // Indecent exposure
replace minSent = 0 if crimecode == 860 // Sexual battery
replace minSent = 3 if crimecode == 865 // Furnishing drugs to a minor
replace minSent = 0 if crimecode == 870 // Child abandoment
replace minSent = 0 if crimecode == 880 // Disrupting school
replace minSent = 0 if crimecode == 882 // Inciting a riot
replace minSent = 0 if crimecode == 884 // Failure to disperse
replace minSent = 0 if crimecode == 886 // Disturbing the peace
replace minSent = 0 if crimecode == 888 // Trespass
replace minSent = 0 if crimecode == 890 // Failure to yield
replace minSent = 0 if crimecode == 900 // Disobeying court order
replace minSent = 0 if crimecode == 901 // Violating restraining order
replace minSent = 0 if crimecode == 902 // Violation of temporary restraining
// order
replace minSent = 0 if crimecode == 903 // Contempt of court
replace minSent = 3 if crimecode == 910 // Kidnap
replace minSent = 1.5 if crimecode == 920 // Attempted kidnap
replace minSent = 0 if crimecode == 922 // Child stealing
replace minSent = 1.33 if crimecode == 924 // Damage to telephone line;
replace minSent = 100 if crimecode == 926 // Train wrecking; The true minimum
//sentence is life without parole, but that is hard to convey to Stata, so I
// think that 100 years is close enough.
replace minSent = 0 if crimecode == 928 // Criminal threats
replace minSent = 0 if crimecode == 930 // Criminal threats w/ no weapon
replace minSent = 0.08 if crimecode == 931 // Brandishing replica firearm
replace minSent = 0 if crimecode == 932 // Peeping tom
replace minSent = 0 if crimecode == 933 // Prowler
replace minSent = 2 if crimecode == 940 // Extortion
replace minSent = 0 if crimecode == 942 // Bribery
replace minSent = 0 if crimecode == 943 // Animal cruelty
replace minSent = 0 if crimecode == 944 // Conspiracy to falsely indict another
replace minSent = 0 if crimecode == 948 // Bigamy
replace minSent = 0 if crimecode == 950 // Theft of services over $400
replace minSent = 0 if crimecode == 951 // Theft of services under $400
replace minSent = 0 if crimecode == 954 // Contributing to the delinquency of
// a minor
replace minSent = 0 if crimecode == 956 // Lewd letters
// Now that I don't need these variables anymore, let's drop them
drop crimecode
drop crimecodedescription
drop areaid
drop datereported
drop dateoccurred
drop victimsex

// Just so we have consistent camelCase across all of our variables.
rename victimage age
save crime_Data_Var.dta, replace

generate age2 = (age)^2

summarize
// II. Regression
regress dateDifference perCapitaCrime age2 age minSent violent weaponUsed female  

// III. Econometric Errors
// III.i Irrelevant Variable Bias
//Checking for irrelevant variable bias.
regress dateDifference perCapitaCrime age minSent violent weaponUsed female  

drop age2

// III.ii Omitted Variable Bias
// Now, we analyze the effect that including race has on our regression.
// First, I generate new variable POC s.t. POC = 0 if the victim is white
// and POC = 1 if the victim is nonwhite.
generate POC = 0
// I drop observations that don't have recordings of the victim's race.
drop if victimdescent == ""
drop if victimdescent == "X"

// Now, let's populate the field.
replace POC = 1 if victimdescent != "W"

// And let's grab some summary statistics.
summarize

// Finally, let's run our regression.
regress dateDifference perCapitaCrime age minSent violent weaponUsed female POC

// III.iii Multicollinearity
//Checking for multicollinearity.
pwcorr

// III.iv Serial Correlation
// Checking for serial correlation
generate time = _n

tsset time

estat dwatson

// Correcting for serial correlation
newey dateDifference perCapitaCrime age minSent violent weaponUsed female POC, lag(0)

// III.v Heteroskedasticity
// Testing for heteroskedasticity
regress dateDifference perCapitaCrime age minSent violent weaponUsed female POC

imtest, white

// Correcting for heteroskedasticity
regress dateDifference perCapitaCrime age minSent violent weaponUsed female POC, robust
save crime_data.dta, replace
set more on
log close
