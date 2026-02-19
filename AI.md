# AI Usage Log

This file records the use of AI tools in this project and notable observations.

---

## Session 1 (Feb 16, 2025)

### What was done
- **Parser.java refactor**: Applied SLAP (Single Level of Abstraction Principle) to shorten the `parse` method. Extracted the parsing logic for each command (`find`, `mark`, `unmark`, `todo`, `deadline`, `event`, `delete`) into separate helper methods.

### Tools used
- Cursor AI (Code Assistant) for refactoring and code generation

### Observations
- **What worked**: Extracting each command type into its own method (`parseFindCommand`, `parseMarkCommand`, etc.) made the main `parse` method much cleaner—it now reads like a table of contents. Each method stays under ~30 LOC as recommended.
- **What worked**: Pre-extracting the `arguments` string from `inputParts` at the top removed the repeated `inputParts.length < 2` checks in each case.
- **Time saved**: ~30–45 min for manual refactoring and testing.

---

*Update this file periodically (e.g., weekly) with new AI tool usage.*
