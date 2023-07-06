import random
responses = {
    "hello" : ["Hello!", "Hi there!", "Greetings!"],
    "how are you" : ["I'm good, thanks!", "I'am doing well!", "All is well!"],
    "bye" : ["Goodbye!", "Farewell!", "Take care!"]
}
def get_response(message):
    message = message.lower()
    if message in responses:
        return random.choice(responses[message])
    else :
        return "I'm sorry, I don't understand"
while True :
    user_input = input("user:")
    response = get_response(user_input)
    print("chatbot", response)
    if user_input.lower() == "Bye":
        break
    