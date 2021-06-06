# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions


# This is a simple example for a custom action which utters "Hello World!"

# from typing import Any, Text, Dict, List
#
# from rasa_sdk import Action, Tracker
# from rasa_sdk.executor import CollectingDispatcher
#
#
# class ActionHelloWorld(Action):
#
#     def name(self) -> Text:
#         return "action_hello_world"
#
#     def run(self, dispatcher: CollectingDispatcher,
#             tracker: Tracker,
#             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#
#         dispatcher.utter_message(text="Hello World!")
#
#         return []
#
# // Custom class example
#
# class MyAction(Action):
#     def name(self) -> Text:
#         return "action_name"
#     async def run(
#         self, dispatcher, tracker: Tracker, domain: Dict[Text, Any],
#     ) -> List[Dict[Text, Any]]:
#         return []


# python library
from typing import Any, Text, Dict, List
# rasa sdk
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher

class ActionInfo(Action):

    def name(self) -> Text:
        return "action_service_info"
    
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
            service = next(tracker.get_latest_entity_values('service'), None)
            searching_service = "none"
            if service is not None:
                searching_service = service
            data = {
                "intent": "action_service_info",
                "service": searching_service
            }
            dispatcher.utter_message(format(data))
            return []

class ActionUsingInfo(Action):
    
    def name(self) -> Text:
        return "action_service_using_info"

    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any],
        ) -> List[Dict[Text, Any]]:
        service = next(tracker.get_latest_entity_values('service'), None)
        search_service = "none"
        if service is not None:
            search_service = service
        data = {
            "intent": "action_service_using_info",
            "service": search_service
        }
        dispatcher.utter_message(format(data))
        return []

class ActionApiList(Action):
    
    def name(self) -> Text:
        return "action_service_api_list"
    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        service = next(tracker.get_latest_entity_values('service'), None)
        searching_service = "none"
        if service is not None:
            searching_service = service
        data = {
            "intent": "action_service_api_list",
            "service": searching_service
        }
        dispatcher.utter_message(format(data))
        return []

class ActionServiceOnly(Action):
    def name(self) -> Text:
        return "action_service_only"
    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        service = next(tracker.get_latest_entity_values('service'), None)
        searching_service = "none"
        if service is not None:
            searching_service = service
        data = {
            "intent": "action_service_only",
            "service": searching_service
        }
        dispatcher.utter_message(format(data))
        return []

class ActionBuildFail(Action):
    def name(self) -> Text:
        return "action_build_fail"
    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        service = next(tracker.get_latest_entity_values('service'), None)
        searching_service = "none"
        if service is not None:
            searching_service = service
        data = {
            "intent": "action_build_fail",
            "service": searching_service
        }
        dispatcher.utter_message(format(data))
        return []

class ActionConnectError(Action):
    def name(self) -> Text:
        return "action_connect_error"
    def run(self, dispatcher: CollectingDispatcher, tracker: Tracker, domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        service = next(tracker.get_latest_entity_values('service'), None)
        searching_service = "none"
        if service is not None:
            searching_service = service
        data = {
            "intent": "action_connect_error",
            "service": searching_service
        }
        dispatcher.utter_message(format(data))
        return []