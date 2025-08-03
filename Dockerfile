FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Install Chrome
RUN apt-get update && \
    apt-get install -y wget gnupg unzip curl && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt install -y ./google-chrome-stable_current_amd64.deb || apt --fix-broken install -y

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Run tests
CMD ["mvn", "clean", "test", "-Denv.url=https://dev-accounts.lezdotechmed.com", "-Dcucumber.filter.tags=@ClientDrive"]