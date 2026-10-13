# Delivery Fee Calculator

A small Java project, built step by step, that prices parcels by weight.
Simple on the surface, with plenty of edge cases underneath.

## Pricing rules (kg)

| Weight | Fee |
|--------|-----|
| 10 to under 20 | 5.00 |
| 20 to under 50 | 12.50 |
| 50 to under 100 | 25.00 |
| 100 and over | 40.00 + 0.30 per started kg over 100 |

Rejected: missing or non-positive weight, under 10 kg, over 500 kg,
and more than 3 decimal places.

## What it practises

- `BigDecimal` for exact money maths and explicit rounding
- Custom checked and unchecked exceptions with typed reasons (enums)
- Immutable objects and defensive copies
- Validating input and configuration up front
- JUnit 5 parameterized tests for boundaries and edge cases

## Run the tests

```bash
mvn test
```

## Roadmap

- [x] Calculator with tiered pricing and cess
- [x] Tier validation (null-safe, non-negative values)
- [x] `TierTable` constructor validation and defensive copy
- [x] `TierTable` contiguity checks (no gaps or overlaps)
- [ ] Calculator driven by `TierTable`
- [ ] Assert rejection reasons in tests

Looking for a partner to learn with? Fork it, pick an unchecked item, and
open a PR.
