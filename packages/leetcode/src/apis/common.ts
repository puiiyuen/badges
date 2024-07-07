import { Enumeration, Str } from 'chanfana'
import { z } from 'zod'
import { difficulties } from '../adapters/leetcode'

export const usernameParam = z.object({
  username: Str({
    required: true,
  }),
})

export const difficultiesParam = z.object({
  difficulty: Enumeration({
    values: difficulties,
    default: 'All',
  }),
})

export const difficultiesParamV1 = z.object({
  difficulty: Enumeration({
    values: difficulties.map((difficulty) => difficulty.toLowerCase()),
    default: 'all',
  }),
})

export const shieldExtraConfigsParams = z.object({
  style: Str({ required: false }),
  label: Str({ required: false }),
  labelColor: Str({ required: false }),
  color: Str({ required: false }),
  logo: Str({ required: false }),
  logoColor: Str({ required: false }),
  logoWidth: Str({ required: false }),
  link: Str({ required: false }),
})

export const successfulResponse = {
  description: 'Successful Response',
  content: {
    'img/svg+xml': {
      schema: Str(),
    },
  },
}
