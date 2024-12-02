import dedent from 'dedent'
export { dedent }
import * as path from '@std/path'

export const sum = (arr) => arr.reduce((acc, val) => acc + val, 0)
export const sliding = (arr, size) => Array(arr.length - size + 1).fill().map((_, i) => arr.slice(i, i + size))

export const mustBe = (a, b) => {
  if (a !== b) {
    const err = new Error()
    console.log(`mustBe() failed actual==${a} expected==${b} ${err.stack.split('\n')[2]}`)
  }
}
export const main = async (dayNr, part1, part2) => {
  const input = await Deno.readTextFile(
    path.resolve(import.meta.dirname, `../problems/day${String(dayNr).padStart(2, '0')}-input.txt`),
  )
  console.log(`part1=${part1(input)}\npart2=${part2(input)}`)
}
