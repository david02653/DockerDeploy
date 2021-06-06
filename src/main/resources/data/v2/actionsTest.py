from rasa_core_sdk import Action
# annotation something
	
class ActionInfo(Action):

	def name(self):
		return "action_service_info"

	def run(self, dispatcher, tracker, domain):
		service = next(tracker.get_latest_entity_values('service'), None)
		searching_service = "none"
		if service is not None:
			searching_service = service
		data = {
			"intent" : "action_service_info",
			"service" : searching_service
		}
		dispatcher.utter_message(format(data))
		return []

class ActionUsingInfo(Action):

	def name(self):
		return "action_service_using_info"

	def run(self, dispatcher, tracker, domain):
		service = next(tracker.get_latest_entity_values('service'), None)
		searching_service = "none"
		if service is not None:
			searching_service = service
		data = {
			"intent" : "action_service_using_info",
			"service" : searching_service
		}
		dispatcher.utter_message(format(data))
		return []

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