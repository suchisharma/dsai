import random 
# Define a dictionary of predefined responses
responses = { 
    "hello": ["Hello!", "Hi there!", "Greetings!"], 
    "how are you": ["I'm good, thanks!", "I'm doing well!", "All is well!"], 
    "bye": ["Goodbye!", "Farewell!", "Take care!"], 
} 
 
# Function to generate a response
def get_response(message):
    message = message.lower()
    if message in responses: 
        return random.choice(responses[message])
    else: 
        return "I'm sorry, I don't understand." 
 
# Main program loop 
while True: 
    user_input = input("User: ")
    response = get_response(user_input) 
    print("ChatBot:", response) 
     
    # Exit the loop if user says "bye"
    if user_input.lower() == "bye": 
        break
