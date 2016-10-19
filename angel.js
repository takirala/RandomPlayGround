var elements = document.getElementsByClassName("interested-note"); 
var interestedButtons = document.getElementsByClassName("g-button blue interested-button"); 
var tipButtons = document.getElementsByClassName("add-note-button tiptip"); 
var sendButtons = document.getElementsByClassName("g-button blue interested-with-note-button"); 

console.log("elements " + elements.length + " buttons " + interestedButtons.length + " tipButtons " + tipButtons.length);

for (var i = 0; i < elements.length; i++) {
	var elem = elements[i];
	//Click on tip message
	tipButtons[i].click();
	// populate message
	var placeText = elem.placeholder;
	//Enter a note to Joshua at Puma Capital.  What excites you about Puma Capital?  Why would you be a good fit?  Which position are you interested in?
	var name = placeText.split(/\s+/)[4];
	var organization = placeText.match("at (.*)\\. ")[1];
	elem.innerText = "Hello "+name+"!\r\n\tRecently, I got to know about "+organization+" through a post on HackerNews. I feel that you guys are building one of the greatest product I have seen in recent past. I believe this idea is here to stay and I want to be a part of the great impact that is gonna come. \r\n\r\nI am currently pursuing my Masters in Computer Science from University of Florida. I have a comprehensive skill set in various technologies acquired over Graduate experience, my 2 years of industrial experience, 3 internships, and Undergrad degree from BITS Pilani. I have proved myself to be a keen learner and can quickly make myself an integral part of a team. I am thrilled at the possibility of being a part of an organization that aces in what it does such as yours.\r\n\r\nI am an extremely passionate developer/builder/game changer. I am someone who never gives up and always walks that extra mile to achieve crafted perfection. All this makes me believe I can be a great asset to "+organization+".";
	
	// send a click
	interestedButtons[i].click();
	sendButtons[i].click();
}