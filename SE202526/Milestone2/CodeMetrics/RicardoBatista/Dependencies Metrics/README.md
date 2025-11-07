# Code Metrics Review — Dependencies

## Overview
This report reviews a static sweep of the codebase for Dependencies revealing pervasive coupling and package cycles. 
The metrics indicate widespread cyclic dependencies and high coupling across core subsystems, increasing maintenance 
cost and fragility.

## Key Findings
- Wide-spread cyclic dependency indicator: many packages show the same high "Cyclic" value.
- Large PDcy / PDpt counts across core packages — strong evidence of entangled package graphs.
- High coupling and size concentrated in: `mindustry.world`, `mindustry.world.blocks`, `mindustry.core`,
`mindustry.content`, `mindustry.type`, `mindustry.ui.dialogs`, `mindustry.ui.fragments`, `mindustry.entities`,
`mindustry.game`, `mindustry.io`, `mindustry.logic`, `mindustry.maps`.

## Hotspots (high risk)
- `mindustry.world` and `mindustry.world.blocks` (many large classes and high method/field counts)
- `mindustry.core`, `mindustry.content`, `mindustry.type`
- UI dialogs / fragments: `mindustry.ui.dialogs`, `mindustry.ui.fragments`
- `mindustry.entities`, `mindustry.game`, `mindustry.io`, `mindustry.logic`, `mindustry.maps`

## Why this matters
- Cycles and high coupling cause:
    - Change ripple effects across unrelated modules
    - Fragile refactors and longer build/test cycles
    - Harder to write focused unit tests
    - Increased cognitive load and bug surface

## Poor Separation of Concerns (SoC)
- Goal of SoC: each module/class has a single responsibility or reason to change.
- Signs of poor SoC: large classes doing many things, frequent inter-package dependencies, duplicated logic.
- Metrics mapping:
    - High LOC and many methods → likely multi-responsibility classes
    - High coupling and cyclic counts → entangled modules preventing independent evolution
