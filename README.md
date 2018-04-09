# Description

This app listen events from IphoneX camera and build graphics of emotions for video user watched (it is backend for IphoneX app).
It also can suggest you videos to watch based on impressions of users with similar tastes.

And of course you can use it as hicup/compojure/mongo boilerplate for your clojure backend.

# Usage

curl -H "Content-Type: application/json" -X POST --data '{ "time": 10, "content-id": "be85f2259ba9726f209bee5fa6db700b", "user-id": "u1", "event-kind":"smiling", "facial-expression" : {"mouthUpperUp_R": 0.06020433, "mouthPress_L": 0.07630153, "mouthLowerDown_L": 0.0704101, "browDown_L": 0.3457011, "cheekPuff": 0.05036864, "mouthShrugLower": 0.08367471, "eyeLookUp_R": 6.303522e-10, "jawLeft": 0.001671119, "eyeBlink_L": 2.628969e-08, "eyeLookIn_L": 0.001062175, "eyeLookOut_R": 4.639507e-09, "mouthShrugUpper": 0.02492044, "mouthFrown_L": -4.093136e-10, "jawForward": 0.03099672, "eyeSquint_R": 0.2603785, "mouthStretch_L": 0.07779928, "jawRight": 0.0005834656, "cheekSquint_R": 0.07429533, "jawOpen": 0.006912689, "eyeWide_L": 0.1193109, "noseSneer_R": 0.09329468, "browOuterUp_L": 0, "eyeWide_R": 0.1193348, "eyeLookDown_R": 0.1730801, "browOuterUp_R": 0, "mouthSmile_R": 0.3278527, "mouthPress_R": 0.0623912, "mouthClose": 0.002654446, "cheekSquint_L": 0.04201935, "eyeLookDown_L": 0.1734764, "mouthRight": 0.006486428, "mouthRollUpper": 0.05356303, "eyeSquint_L": 0.2593125, "mouthRollLower": 0.03344278, "mouthStretch_R": 0.06844464, "mouthDimple_L": 0.1667701, "mouthUpperUp_L": 0.06346847, "mouthPucker": 0.03560872, "noseSneer_L": 0.08950436, "browDown_R": 0.3444795, "browInnerUp": 0.04937833, "mouthLowerDown_R": 0.06656003, "eyeLookUp_L": 6.275244e-10, "eyeLookIn_R": 0.1168476, "mouthFunnel": 0.06098516, "mouthFrown_R": -5.985869e-10, "eyeLookOut_L": 0.005228126, "mouthLeft": 2.528459e-05, "mouthDimple_R": 0.1446124, "eyeBlink_R": 2.62948e-08, "mouthSmile_L": 0.3426998}}' http://localhost:3000/api/events

curl -H "Content-Type: application/json" -X POST --data '{"content-id": "c1", "user-id": "u1", "event-kind":"smiling", "time": 10}' https://boiling-river-75527.herokuapp.com/api/events

https://boiling-river-75527.herokuapp.com/site/dashboard
