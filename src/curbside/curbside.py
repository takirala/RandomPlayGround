# Simplified API:
# /get-session -> return a json object with {"session-id": "some_hash_string"}
# /hash_id -> if your session is valid:
#                return a json object with {"next": ["hash_1", ..., "hash_n"]},
#                and optionally "secret" with a letter
#             else: {"error": "Get a new session"}
import requests

BASE_URL = "http://challenge.shopcurbside.com/"

visited = set()
secret = ""


def get_session():
  return requests.get(BASE_URL + "/get-session").content


def good_object(obj):
  for key, value in list(obj.items()):
    obj[key.lower()] = value
  return obj


def get_secret(content):
  return content.get('secret', '')


def get_next(next):
  if isinstance(next, list): 
    for node_id in next:
      yield BASE_URL + node_id
  else:
    yield BASE_URL + next


def process_node(url, session):
  global visited, secret
  content = good_object(requests.get(url, headers={'Session': session}).json())
  
  if "error" in content:
    content = good_object(requests.get(url, headers={'Session': get_session()}).json())
    print("Error occured")

  s = get_secret(content)
  secret += s
  print(content.get('next', []))
  print(secret)
  for node in get_next(content.get('next', [])):
    if node not in visited:
      visited.add(node)
      process_node(node, session)


process_node(BASE_URL + "start", get_session())
print("Secret {}".format(secret))