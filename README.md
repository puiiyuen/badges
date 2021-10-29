# Badges

## LeetCode

### Usage
``` markdown
https://badge.peiyuan.ch/leetcode/{username}/{option}<query>
```
| Parameter | Value | Required | Default |
|:---:|:---:|:---:|:---:|
| username | LeetCode Username | Yes | N/A |
| option | [Option Object](#option-object) | Yes | N/A |
| query | Depends on option | No | Depends on option |

### Option Object
#### `submission`
| Query | Value | Default | Description |
|:---:|:---:|:---:| --- |
| accepted | `true` / `false` | `false` | Accepted Submissions or Total Submissions |
| difficulty | `all` / `easy` / `medium` / `hard` | `all` | Identity the submission of difficulty

#### Sample
Accepted Submissions

|All|Easy|Medium|Hard|
|---|---|---|---|
|[![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?accepted=true)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?accepted=true&difficulty=easy)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?accepted=true&difficulty=medium)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?accepted=true&difficulty=hard)]() |

Total Submissions

|All|Easy|Medium|Hard|
|---|---|---|---|
|[![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?difficulty=easy)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?difficulty=medium)]() | [![](https://badge.peiyuan.ch/leetcode/puiiyuen/submission?difficulty=hard)]() |
