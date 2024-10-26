This is specification of music, describable by music notation, implemented in
this project.

> [!WARNING]
> This document may contain unconventional designations for the sake of compactness,
> clarity and consistency among the documents.

> [!NOTE] 
> It is compulsory to use local designations in contributed code.

> [!NOTE]
> Local document-scope designations (like *note* for *musical note*) are local, and 
> can be overridden in other documents

# Music elements
## Musical Note
*Musical note* (hereinafter, *note*) is a sound of fixed frequency $\nu$, played for
some period of time $t$.  
*Pitch* $p$ of the note can be calculated as $p=9 + 6\cdot\log_2{\frac{\nu}{440~\mathrm{Hz}}}$.
It can be any whole number from $-39$ to $48$ inclusively. So, $t=440~\mathrm{Hz} \cdot 2^
{\frac{p-9}6}$.  
*Note value* of the note $q$ can be calculated as $q=\frac{t\cdot B}{240}$, where $B$ is
number of beats per minute. So, $t = \frac{240q}B$.

## Musical Chord
*Musical chord* (hereinafter, *chord*) is several (q or more) musical notes, sounding at the 
same time.

## Musical line
*Musical line* (hereinafter, *line*) is a collection of musical chords, going one
after another.

# Music terms
## Octave
*Octave value* (also, *octave*) of a note is a value $o = \frac{p}{12} + 4$. *Octave* is a collection of all notes 
with given *octave value*. Each *octave* has unique name, listed in the table below:

| octave name | octave value |
|-------------|--------------|
| fourth      | 7            |
| third       | 6            |
| second      | 5            |
| first       | 4            |
| small       | 3            |
| great       | 2            |
| contra      | 1            |
| sub-contra  | 0            |

## Natural note
Let $r$ be remainder of division of *note*'s *pitch* by 12. *Notes* with $r$ equal to numbers,
listed in the table below, are called *natural*.

| $r$ | letter |
|-----|--------|
| 0   | `C`    |
| 2   | `D`    |
| 4   | `E`    |
| 5   | `F`    |
| 7   | `G`    |
| 9   | `A`    |
| 11  | `B`    |

*Natural note* can be represented by a string, consisting of it's letter (from the table above) and
its octave, like that: note with $p=23$ is `B5`, note with $p=-7$ is `F3`.

## Non natural note
*Non natural notes* with a pitch one semitone higher, than a *natural note*, can be represented by 
the same letter with "#" symbol applied, if it is one semitone lower, "b" is applied: note with 
note with $p=22$ is `Bb5`, note with $p=-6$ is `F#3`. We can see, that "#" cannot be applied to 
`E` and `B`, and "b" cannot be applied to `F` and `C`.

