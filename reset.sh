#!/usr/bin/env bash
# Reset one or all stages back to the pristine TODO stub, so you can redo a drill
# from scratch. Usage:
#   ./reset.sh stage3      # reset just stage3
#   ./reset.sh all         # reset every stage
set -euo pipefail

DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
STAGES=(stage2 stage3 stage4 stage5 stage6)

reset_stage() {
  local stage="$1"
  local src_dir="$DIR/src/main/java/practice/$stage"
  local stub_dir="$DIR/stubs/$stage"
  if [ ! -d "$stub_dir" ]; then
    echo "Unknown stage: $stage" >&2
    exit 1
  fi
  cp "$stub_dir"/*.java "$src_dir"/
  echo "Reset $stage"
}

if [ "${1:-}" = "all" ]; then
  for s in "${STAGES[@]}"; do
    reset_stage "$s"
  done
elif [ -n "${1:-}" ]; then
  reset_stage "$1"
else
  echo "Usage: ./reset.sh <stage2|stage3|stage4|stage5|stage6|all>" >&2
  exit 1
fi
