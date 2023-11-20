# rewards
provided 2 rest apis

# Api to fetch individual customer rewards for last 3 month transactions
Request: http://localhost:8080/getRewardsByCustId/2
Response: [{"customerName":"chris","monthlyPoints":{"9":1254,"10":110,"11":220},"totalPoints":1584}]

# Api to fetch all customer rewards for last 3 month transactions
Request: http://localhost:8080/getRewards
Response: [{"customerName":"chris","monthlyPoints":{"9":1254,"10":110,"11":220},"totalPoints":1584},{"customerName":"Rick","monthlyPoints":{"9":1254,"10":140,"11":380},"totalPoints":1774}]

