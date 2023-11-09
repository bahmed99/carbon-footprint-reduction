const scanner = require('sonarqube-scanner');

scanner(
    {
        serverUrl: 'http://localhost:9000',
        token: "sqa_b8601a1dc8534730be7de148d2db514e18a852df",
        options: {
            'sonar.projectName': 'frontEnd',
        }
    },
    () => process.exit()
)