# Usage

curl -H "Content-Type: application/json" -X POST --data '{"content-id": "c1", "user-id": "u1", "event-kind":"smiling", "time": 10}' http://localhost:3000/api/events

curl -H "Content-Type: application/json" -X POST --data '{"content-id": "c1", "user-id": "u1", "event-kind":"smiling", "time": 10}' https://boiling-river-75527.herokuapp.com/
