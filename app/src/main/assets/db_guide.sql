BEGIN TRANSACTION;
CREATE TABLE "tips" (
	`_id`	TEXT,
	`Tip`	TEXT
);
INSERT INTO `tips` VALUES ('T1','Venue ? select a good venue for the numbers expected which doesn?t cost too much. It may be free if they provide a bar.');
INSERT INTO `tips` VALUES ('T2','Advertise the event in advance. People need to know the date and time in advance to come along.');
INSERT INTO `tips` VALUES ('T3','Remember to allow fro all the cost when setting prices e.g. the cost of the stall.');
INSERT INTO `tips` VALUES ('T4','Make sure people know you are raising money for a charitable cause, they are more likely to pay more.');
INSERT INTO `tips` VALUES ('T5','Presentation counts. The items need to be sen to be sold.');
INSERT INTO `tips` VALUES ('T6','Have a float with plenty of change for the day so you don''t loss sales.');
INSERT INTO `tips` VALUES ('T7','');
INSERT INTO `tips` VALUES ('T8','');
INSERT INTO `tips` VALUES ('T9','');
INSERT INTO `tips` VALUES ('T10','');
INSERT INTO `tips` VALUES ('T11','');
INSERT INTO `tips` VALUES ('T12','');
INSERT INTO `tips` VALUES ('T13','');
INSERT INTO `tips` VALUES ('T14','');
INSERT INTO `tips` VALUES ('T15','');
INSERT INTO `tips` VALUES ('T16','');
INSERT INTO `tips` VALUES ('T17','');
INSERT INTO `tips` VALUES ('T18','');
INSERT INTO `tips` VALUES ('T19','');
INSERT INTO `tips` VALUES ('T20','');
INSERT INTO `tips` VALUES ('T21','');
INSERT INTO `tips` VALUES ('T22','');
INSERT INTO `tips` VALUES ('T23','');
INSERT INTO `tips` VALUES ('T24','');
INSERT INTO `tips` VALUES ('T25','');
INSERT INTO `tips` VALUES ('T26','');
CREATE TABLE "guide_tips" (
	`_id`	TEXT,
	`Tip_id`	TEXT
);
INSERT INTO `guide_tips` VALUES ('G1','T2');
INSERT INTO `guide_tips` VALUES ('G1','T3');
INSERT INTO `guide_tips` VALUES ('G1','T4');
INSERT INTO `guide_tips` VALUES ('G1','T5');
INSERT INTO `guide_tips` VALUES ('G1','T6');
CREATE TABLE "guide" (
	`_id`	TEXT,
	`Title`	TEXT,
	`Expected_Amount`	TEXT,
	`How_It_works`	TEXT
);
INSERT INTO `guide` VALUES ('G1','Car Boot Sale','�200 to �500','Selling items is a good way to raise money and can be repeated several times as people are getting something for their money as well as contributing to your volunteering overseas. If you are selling items to the genral public it also means that you are collecting money from a wider group  and are not asking the same peopel to contribute all the time.
Collect the items to be sold which can be donated, where you get the full price, or bought, were you get the profit in the sales. Remenber to set the price at the approrpiate level for the location to get as much as possible but not appear to high that they go to other stalls.');
INSERT INTO `guide` VALUES ('G2','','','');
INSERT INTO `guide` VALUES ('G3','','','');
INSERT INTO `guide` VALUES ('G4','','','');
INSERT INTO `guide` VALUES ('G5','','','');
INSERT INTO `guide` VALUES ('G6','','','');
INSERT INTO `guide` VALUES ('G7','','','');
INSERT INTO `guide` VALUES ('G8','','','');
INSERT INTO `guide` VALUES ('G9','','','');
INSERT INTO `guide` VALUES ('G10','','','');
INSERT INTO `guide` VALUES ('G11','','','');
INSERT INTO `guide` VALUES ('G12','','','');
CREATE TABLE `android_metadata` (
	`locale`	TEXT
);
INSERT INTO `android_metadata` VALUES ('en_US');
COMMIT;
