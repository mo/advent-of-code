import * as path from '@std/path'

export const sum = (arr) => arr.reduce((acc, val) => acc + val, 0)

export const main = async (dayNr, part1, part2) => {
  const input = await Deno.readTextFile(
    path.resolve(import.meta.dirname, `../problems/day${String(dayNr).padStart(2, '0')}-input.txt`),
  )
  console.log(`part1=${part1(input)}\npart2=${part2(input)}`)
}
