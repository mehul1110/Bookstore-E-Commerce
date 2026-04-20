# Git Flow

## Main branches

- `main`
- `develop`

## Working branches

- `feature/<service-name>`
- `feature/<service-name>-<capability>`
- `release/<version>`
- `hotfix/<issue>`

## Typical flow

1. `git checkout develop`
2. `git pull origin develop`
3. `git checkout -b feature/user-service`
4. build and verify locally
5. push and open PR into `develop`
6. promote `develop` into `main`

