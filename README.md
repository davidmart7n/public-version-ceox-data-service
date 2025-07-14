# CEOX Data Service — Public Version 🧩

This repository contains a **public and stripped-down version** of the backend microservice **CEOX Data Service**, part of a larger system for intelligent weekly business reporting.

> ⚠️ This is **not the complete source code**. Sensitive logic, internal services, and confidential integrations have been **removed, emptied or heavily abstracted**.

---

## 📦 What's included

- The full **project structure** with packages, models, and services.
- Selected service and model classes to illustrate the architecture.
- Clear **inline comments** to help readers understand the structure.
- Some files and folders are **empty or contain only** `// private document` markers, or are just hided.

---

## 🔐 What’s been removed

To protect business logic, API keys, and proprietary components, this public version **excludes or strips** the following:

- Sensitive config files like `application.properties`.
- Classes tied to external services such as Gemini API, PDF generation or Markmap rendering.
- Cloud functions, key business rules, or internal algorithms.
- Any deployment configuration (environments, secrets, CI/CD scripts).

---

## 💡 Why release a public version?

This repo is shared to:

- Provide a **transparent view** of how a real-world Spring Boot microservice is structured.
- Allow developers to learn from or adapt architectural decisions and class design.
- Showcase modular design, layered architecture, and REST service patterns.
- Demonstrate team-level practices while keeping business-specific logic private.

---

## 🚀 Tech Stack (used in the full system)

- **Java 21**
- **Spring Boot 3**
- **Maven**
- **Google Gemini API**
- **Markmap (Node.js)**
- **OpenHTMLtoPDF**
- **Firestore (Firebase)**

> Many of these integrations have been removed in this version, or replaced with placeholders for safety.

---

## ⚠️ License & Usage

This public version is provided for **educational and demonstrative purposes only**.  
All rights to the complete system and brand remain with the original developers.  
**Do not reuse or redistribute this project in production.**

---

Feel free to explore the structure, follow the comments, and reach out if you're curious about the full CEOX system.

<p align="center"><i>— Built with purpose and privacy by the Maen Studios team 🛡️</i></p>
