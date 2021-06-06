from rasa_core_sdk import Action
# test set 2

class ActionApiList(Action):

	def name(self):
		return "action_service_api_list"

	def run(self, dispatcher, tracker, domain):
		service = next(tracker.get_latest_entity_values('service'), None)
		searching_service = "none"
		if service is not None:
			searching_service = service
		data = {
			"intent" : "action_service_api_list",
			"service" : searching_service
		}
		dispatcher.utter_message(format(data))
		return []

class ActionServiceOnly(Action):
# annotation something
	def name(self):
		return "action_service_only"

	def run(self, dispatcher, tracker, domain):
		service = next(tracker.get_latest_entity_values('service'), None)
		searching_service = "none"
		if service is not None:
			searching_service = service
		data = {
			"intent" : "action_service_only",
			"service" : searching_service
		}
		dispatcher.utter_message(format(data))
		return []